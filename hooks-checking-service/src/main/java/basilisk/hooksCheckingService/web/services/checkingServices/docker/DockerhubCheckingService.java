package basilisk.hooksCheckingService.web.services.checkingServices.docker;

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
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
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
                checkForNewVersions(dockerRepo);
                System.out.println();
            } catch (DockerhubException e) {
                //ToDo log
                System.out.println("not valid  thing");
            }
            //
        }
    }

    private void checkForNewVersions(DockerRepo dockerRepo) throws DockerhubException {
        try {

            DockerHubRestProxy dockerHubRestProxy = new DockerHubRestProxy(new RestTemplateBuilder());
            var retrievedTags = dockerHubRestProxy.getTages(dockerRepo.getOwnerName(), dockerRepo.getRepoName());
            for (DockerApiTag apiTag : retrievedTags) {

                // check whether the image assigned to the tag exists
                DockerImage dockerImage;
                var savedDockerImage = dockerImageRepository.findByDigest(apiTag.getImages().get(0).getDigest());
                //if the image assigned to the tag does not exists, create it.
                if (savedDockerImage.isEmpty()) {
                    //check for an execption where a tag has no image
                    //ToDo: inform the user or log the information
                    if(apiTag.getImages().get(0)==null || apiTag.getImages().get(0).getDigest()==null)
                        continue;
                    dockerImage = new DockerImage(apiTag.getImages().get(0).getDigest(), apiTag.getImages().get(0).getLastPushed(), dockerRepo, new HashSet<>());
                    // save the image in the database
                    dockerImageRepository.save(dockerImage);
                    // send it to the queue
                    messagingHandler.send(dockerImage);
                } else {
                    dockerImage = savedDockerImage.get();
                }

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
        catch (MessageSendingExecption e)
        {

        }
        catch (Exception e)
        {
            throw new DockerhubException();
        }

    }
}
