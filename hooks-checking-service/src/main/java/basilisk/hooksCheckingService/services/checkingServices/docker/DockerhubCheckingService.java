package basilisk.hooksCheckingService.services.checkingServices.docker;

import basilisk.hooksCheckingService.core.exception.DockerhubException;
import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.domain.docker.DockerTag;
import basilisk.hooksCheckingService.events.DockerImageCreatedEvent;
import basilisk.hooksCheckingService.events.DockerTagAddedEvent;
import basilisk.hooksCheckingService.events.DockerTagUpdatedEvent;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.DockerTagRepository;
import basilisk.hooksCheckingService.services.checkingServices.CheckingService;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import basilisk.hooksCheckingService.web.proxies.docker.DockerApiTag;
import basilisk.hooksCheckingService.web.proxies.docker.DockerHubRestProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;


/**
 * @author Fakhr Shaheen, Fabian Renisng
 */
public class DockerhubCheckingService implements CheckingService {


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


    /**
     * checks for new images and tags in a docker repo.
     */
    private void checkRepo(DockerRepo dockerRepo) throws DockerhubException {

        var retrievedTags = this.dockerHubRestProxy.getTags(dockerRepo.getRepoOwner(), dockerRepo.getRepoName());
        for (DockerApiTag apiTag : retrievedTags) {
            try {

                DockerImage dockerImage = processDockerImage(apiTag, dockerRepo);
                processDockerTag(apiTag, dockerImage);
            } catch (MessageSendingExecption e) {

            } catch (Exception e) {
                throw new DockerhubException();
            }
        }
    }


    /**
     * checks whether the docker image is checked before, and acts accordingly.
     *
     * @param apiTag
     * @param dockerRepo
     * @return
     * @throws MessageSendingExecption
     * @throws DockerhubException
     */
    private DockerImage processDockerImage(DockerApiTag apiTag, DockerRepo dockerRepo) throws MessageSendingExecption, DockerhubException {
        // check whether the image assigned to the tag exists
        DockerImage dockerImage;
        var savedDockerImage = dockerImageRepository.findByDigest(apiTag.getImages().get(0).getDigest());
        //if the image assigned to the tag does not exists, create it.
        if (savedDockerImage.isEmpty()) {
            //check for an execption where a tag has no image
            //ToDo: inform the user or log the information
            if (apiTag.getImages().get(0) == null || apiTag.getImages().get(0).getDigest() == null)
                throw new DockerhubException();
            dockerImage = new DockerImage(apiTag.getImages().get(0).getDigest(), apiTag.getImages().get(0).getLastPushed(), dockerRepo, new HashSet<>());
            // save the image in the database
            dockerImageRepository.save(dockerImage);
            // send it to the queue
            DockerImageCreatedEvent imageCreatedEvent = DockerImageCreatedEvent.builder().digest(dockerImage.getDigest()).repoId(dockerRepo.getId()).build();
            messageSender.send(imageCreatedEvent);
        } else {
            dockerImage = savedDockerImage.get();
        }
        return dockerImage;

    }

    /**
     * * checks whether the docker tag is checked before, and acts accordingly.
     *
     * @param apiTag
     * @param dockerImage
     */
    private void processDockerTag(DockerApiTag apiTag, DockerImage dockerImage) throws MessageSendingExecption {
        // check whether the tag exists
        var savedTag = dockerTagRepository.findDockerTagByName(apiTag.getName());
        // If the tag is not stored
        if (savedTag.isEmpty()) {
            var x4 = new DockerTag(apiTag.getName(), apiTag.getTagLastPushed(), dockerImage);
            //Add the tag to the database
            dockerTagRepository.save(new DockerTag(apiTag.getName(), apiTag.getTagLastPushed(), dockerImage));
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
            if (savedTag.get().getDockerImage() == apiTag.getImageId()) {
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
