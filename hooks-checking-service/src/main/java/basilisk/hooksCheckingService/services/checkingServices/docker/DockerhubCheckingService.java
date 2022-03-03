package basilisk.hooksCheckingService.services.checkingServices.docker;

import basilisk.hooksCheckingService.core.exception.DockerhubException;
import basilisk.hooksCheckingService.events.docker.DockerTagAddedEvent;
import basilisk.hooksCheckingService.events.docker.DockerTagUpdatedEvent;
import basilisk.hooksCheckingService.model.docker.DockerImage;
import basilisk.hooksCheckingService.model.docker.DockerRepo;
import basilisk.hooksCheckingService.model.docker.DockerTag;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.DockerTagRepository;
import basilisk.hooksCheckingService.services.checkingServices.CheckingService;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import basilisk.hooksCheckingService.web.proxies.docker.DockerApiTag;
import basilisk.hooksCheckingService.web.proxies.docker.DockerHubRestProxy;
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
            processDockerTag(apiTag, dockerImage);
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

            dockerImage = new DockerImage(apiTag.getImages().get(0).getDigest(), apiTag.getImages().get(0).getLastPushed(), dockerRepo);

            this.dockerImageRepository.save(dockerImage);

            // DockerImageCreatedEvent imageCreatedEvent = DockerImageCreatedEvent.builder().digest(dockerImage.getDigest()).repoId(dockerRepo.getId()).build();
            // messageSender.send(imageCreatedEvent);
        } else {
            dockerImage = savedDockerImage.get();
        }
        return dockerImage;

    }

    private void processDockerTag(DockerApiTag apiTag, DockerImage dockerImage) {
        Optional<DockerTag> savedTag = this.dockerTagRepository.findDockerTagByName(apiTag.getName());

        if (savedTag.isEmpty()) {
            //Add the tag to the database
            this.dockerTagRepository.save(new DockerTag(apiTag.getName(), apiTag.getTagLastPushed(), dockerImage));
            // send docker tag added event
            DockerTagAddedEvent addedEvent = DockerTagAddedEvent.builder()
                    .imageId(dockerImage.getId())
                    .name(apiTag.getName())
                    .lastPushedDate(apiTag.getTagLastPushed())
                    .build();
            messageSender.send(addedEvent);
            // if the tag already exists
        } else {
            // If the image of the tag has not been changed then the tag is the same, do nothing for now.
            if (savedTag.get().getDockerImage().getId() == apiTag.getImageId()) {
            }
            // Otherwise, the tag has been assigned with another image.
            else {
                savedTag.get().setDockerImage(dockerImage);
                //save the tag
                dockerTagRepository.save(savedTag.get());
                //send an event that the tag has been assigned to another image
                DockerTagUpdatedEvent updatedEvent = DockerTagUpdatedEvent.builder()
                        .imageId(dockerImage.getId())
                        .name(savedTag.get().getName())
                        .lastPushedDate(savedTag.get().getLastPushedDate())
                        .build();
                messageSender.send(updatedEvent);

            }
        }
    }
}
