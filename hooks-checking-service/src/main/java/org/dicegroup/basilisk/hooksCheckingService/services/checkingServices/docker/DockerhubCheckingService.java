package org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.docker;

import org.dicegroup.basilisk.hooksCheckingService.core.exception.DockerhubException;
import org.dicegroup.basilisk.hooksCheckingService.events.docker.DockerTagEvent;
import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerImage;
import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerTag;
import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerImageRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerTagRepository;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.CheckingService;
import org.dicegroup.basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.dicegroup.basilisk.hooksCheckingService.web.proxies.docker.DockerApiImage;
import org.dicegroup.basilisk.hooksCheckingService.web.proxies.docker.DockerApiTag;
import org.dicegroup.basilisk.hooksCheckingService.web.proxies.docker.DockerHubRestProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class DockerhubCheckingService implements CheckingService {

    private static final Logger logger = LoggerFactory.getLogger(DockerhubCheckingService.class);

    DockerImageRepository dockerImageRepository;
    DockerRepoRepository dockerRepoRepository;
    DockerTagRepository dockerTagRepository;
    MessageSender messageSender;

    @Autowired
    DockerHubRestProxy dockerHubRestProxy;

    public DockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, MessageSender messageSender) {
        this.dockerImageRepository = dockerImageRepository;
        this.dockerRepoRepository = dockerRepoRepository;
        this.dockerTagRepository = dockerTagRepository;
        this.messageSender = messageSender;
    }

    @Override
    public void performChecking() {
        //get the dockerhub repos
        Iterable<DockerRepo> dockerRepos = this.dockerRepoRepository.findAll();
        //go through them
        for (DockerRepo dockerRepo : dockerRepos) {
            //do the logic for checking
            try {
                checkRepo(dockerRepo);
            } catch (DockerhubException e) {
                //ToDo log
                System.out.println("not valid  thing");
            }
        }
    }


    private void checkRepo(DockerRepo dockerRepo) throws DockerhubException {

        var retrievedTags = this.dockerHubRestProxy.getTags(dockerRepo.getRepoOwner(), dockerRepo.getRepoName());
        for (DockerApiTag apiTag : retrievedTags) {
            DockerImage dockerImage = getDockerImage(apiTag, dockerRepo);
            processDockerTag(dockerRepo, apiTag, dockerImage);
        }
    }


    private DockerImage getDockerImage(DockerApiTag apiTag, DockerRepo dockerRepo) throws DockerhubException {
        DockerImage dockerImage;
        Optional<DockerImage> savedDockerImage = this.dockerImageRepository.findByDigest(apiTag.getImages().get(0).getDigest());

        if (savedDockerImage.isEmpty()) {
            logger.info("Docker Image {} of repo {} not found in database! Adding Image..", apiTag.getName(), dockerRepo.getRepoName());

            if (apiTag.getImages().get(0) == null || apiTag.getImages().get(0).getDigest() == null) {
                throw new DockerhubException();
            }

            DockerApiImage apiImage = apiTag.getImages().get(0);
            dockerImage = DockerImage.builder()
                    .digest(apiImage.getDigest())
                    .dockerRepo(dockerRepo)
                    .build();

            dockerImage = this.dockerImageRepository.save(dockerImage);
        } else {
            dockerImage = savedDockerImage.get();
        }
        return dockerImage;

    }

    private void processDockerTag(DockerRepo repo, DockerApiTag apiTag, DockerImage dockerImage) {
        Optional<DockerTag> savedTag = this.dockerTagRepository.findDockerTagByDockerRepoAndName(repo, apiTag.getName());

        if (savedTag.isEmpty()) {
            //Add the tag to the database
            DockerTag newTag = DockerTag.builder()
                    .name(apiTag.getName())
                    .dockerRepo(repo)
                    .dockerImage(dockerImage)
                    .build();

            this.dockerTagRepository.save(newTag);
            // send docker tag added event
            DockerTagEvent event = DockerTagEvent.builder()
                    .repo(repo)
                    .imageDigest(dockerImage.getDigest())
                    .tagName(apiTag.getName())
                    .build();
            this.messageSender.send(event);

        } else {
            DockerTag tag = savedTag.get();

            if (!tag.getDockerImage().equals(dockerImage)) {
                tag.setDockerImage(dockerImage);
                dockerTagRepository.save(savedTag.get());

                DockerTagEvent updatedEvent = DockerTagEvent.builder()
                        .repo(repo)
                        .tagName(tag.getName())
                        .imageDigest(dockerImage.getDigest())
                        .build();

                messageSender.send(updatedEvent);

            }
        }
    }
}
