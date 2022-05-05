package org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.docker;

import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.events.hooks.hook.DockerTagEvent;
import org.dicegroup.basilisk.hooksCheckingService.core.exception.DockerhubException;
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

import java.util.List;
import java.util.Optional;


@Slf4j
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
        for (DockerRepo dockerRepo : this.dockerRepoRepository.findAll()) {
            try {
                checkRepo(dockerRepo);
            } catch (DockerhubException e) {
                log.error("DockerhubException while checking");
            }
        }
    }


    private void checkRepo(DockerRepo dockerRepo) throws DockerhubException {
        List<DockerApiTag> retrievedTags = this.dockerHubRestProxy.getTags(dockerRepo.getRepoOwner(), dockerRepo.getRepoName());
        for (DockerApiTag apiTag : retrievedTags) {
            DockerImage dockerImage = getDockerImage(apiTag, dockerRepo);
            processDockerTag(dockerRepo, apiTag, dockerImage);
        }
    }


    private DockerImage getDockerImage(DockerApiTag apiTag, DockerRepo dockerRepo) {
        String digest = apiTag.getImages().get(0).getDigest();
        Optional<DockerImage> imageOpt = this.dockerImageRepository.findByDockerRepoAndDigest(dockerRepo, digest);

        if (imageOpt.isPresent()) {
            return imageOpt.get();
        }

        logger.info("Docker Image {} of repo {} not found in database! Adding Image..", apiTag.getName(), dockerRepo.getRepoName());
        DockerImage dockerImage = DockerImage.builder()
                .digest(digest)
                .dockerRepo(dockerRepo)
                .build();

        return this.dockerImageRepository.save(dockerImage);
    }

    private void processDockerTag(DockerRepo repo, DockerApiTag apiTag, DockerImage dockerImage) {
        Optional<DockerTag> tagOpt = this.dockerTagRepository.findDockerTagByDockerRepoAndName(repo, apiTag.getName());

        if (tagOpt.isEmpty()) {
            //Add the tag to the database
            DockerTag newTag = DockerTag.builder()
                    .name(apiTag.getName())
                    .dockerRepo(repo)
                    .dockerImage(dockerImage)
                    .build();

            this.dockerTagRepository.save(newTag);
            // send docker tag added event
            DockerTagEvent event = DockerTagEvent.builder()
                    .repoId(repo.getId())
                    .imageDigest(dockerImage.getDigest())
                    .tagName(apiTag.getName())
                    .build();
            log.info("sending DockerTagEvent {}", event);
            this.messageSender.send(event);

        } else {
            DockerTag tag = tagOpt.get();

            if (!tag.getDockerImage().getId().equals(dockerImage.getId())) {
                tag.setDockerImage(dockerImage);
                tag = this.dockerTagRepository.save(tag);

                DockerTagEvent updatedEvent = DockerTagEvent.builder()
                        .repoId(repo.getId())
                        .tagName(tag.getName())
                        .imageDigest(dockerImage.getDigest())
                        .build();

                log.info("sending DockerTagEvent update {}", updatedEvent);
                this.messageSender.send(updatedEvent);

            }
        }
    }
}
