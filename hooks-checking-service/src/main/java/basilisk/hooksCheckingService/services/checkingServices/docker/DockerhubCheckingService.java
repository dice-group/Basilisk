package basilisk.hooksCheckingService.services.checkingServices.docker;

import basilisk.hooksCheckingService.core.exception.DockerhubException;
import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.domain.docker.DockerTag;
import basilisk.hooksCheckingService.messaging.MessagingHandler;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.DockerTagRepository;
import basilisk.hooksCheckingService.web.proxies.DockerHubRestProxy;
import basilisk.hooksCheckingService.services.checkingServices.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import basilisk.hooksCheckingService.domain.docker.api.*;

import java.util.HashSet;


/**
 * @author Fakhr Shaheen
 */
public class DockerhubCheckingService implements CheckingService {


    DockerImageRepository dockerImageRepository;
    DockerRepoRepository dockerRepoRepository;
    DockerTagRepository dockerTagRepository;
    MessagingHandler messagingHandler;

    @Autowired
    DockerHubRestProxy dockerHubRestProxy;

    public DockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, MessagingHandler messagingHandler) {
        this.dockerImageRepository = dockerImageRepository;
        this.dockerRepoRepository = dockerRepoRepository;
        this.dockerTagRepository = dockerTagRepository;
        this.messagingHandler = messagingHandler;
    }

    @Override
    public void performChecking() {
        //get the dockerhub repos
        Iterable<DockerRepo> dockerRepos = dockerRepoRepository.findAll();
        //go through them
        for (DockerRepo dockerRepo : dockerRepos) {
            //do the logic for checking
            try {
                checkRepo(dockerRepo);
                System.out.println();
            } catch (DockerhubException e) {
                //ToDo log
                System.out.println("not valid  thing");
            }
            //
        }
    }


    /**
     * checks for new images and tags in a docker repo.
     *
     * @param dockerRepo
     * @throws DockerhubException
     */
    private void checkRepo(DockerRepo dockerRepo) throws DockerhubException {

        var retrievedTags = dockerHubRestProxy.getTages(dockerRepo.getOwnerName(), dockerRepo.getRepoName());
        for (DockerApiTag apiTag : retrievedTags) {

            DockerImage dockerImage = null;
            try {

                dockerImage = processDockerImage(apiTag, dockerRepo);
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
            messagingHandler.send(dockerImage);
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
    private void processDockerTag(DockerApiTag apiTag, DockerImage dockerImage) {
        // check whether the tag exists
        var savedTag = dockerTagRepository.findDockerTagByName(apiTag.getName());
        // If the tag is not stored
        if (savedTag.isEmpty()) {
            var x4 = new DockerTag(apiTag.getName(), apiTag.getTagLastPushed(), dockerImage);
            //Add the tag to the database
            dockerTagRepository.save(new DockerTag(apiTag.getName(), apiTag.getTagLastPushed(), dockerImage));

            // if the tag already exists
        } else {
            // If the image of the tag has not been changed then the tag is the same, do nothing.
            if (savedTag.get().getDockerImage() == apiTag.getImageId()) {
            }
            // Otherwise, the tag has been assigned with another image.
            else {
                savedTag.get().setDockerImage(dockerImage);
                dockerTagRepository.save(savedTag.get());
            }
        }
    }
}
