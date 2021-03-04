package basilisk.hooksCheckingService.web.services.checkingServices.docker;

import basilisk.hooksCheckingService.core.exception.DockerhubException;
import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.domain.docker.DockerTag;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.DockerTagRepository;
import basilisk.hooksCheckingService.web.proxies.DockerHubRestProxy;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import basilisk.hooksCheckingService.domain.docker.api.*;

import java.util.Date;
import java.util.HashSet;


/**
 * @author Fakhr Shaheen
 */
public class DockerhubCheckingService implements CheckingService {


    DockerImageRepository dockerImageRepository;
    DockerRepoRepository dockerRepoRepository;
    DockerTagRepository dockerTagRepository;
    HookMessageSender hookMessageSender;

    public DockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, HookMessageSender hookMessageSender) {
        this.dockerImageRepository = dockerImageRepository;
        this.dockerRepoRepository = dockerRepoRepository;
        this.dockerTagRepository = dockerTagRepository;
        this.hookMessageSender = hookMessageSender;
    }

    @Override
    public void performChecking() {
        //get the dockerhub repos
        Iterable<DockerRepo> dockerRepos = dockerRepoRepository.findAll();
        //go through them
        for (DockerRepo dockerRepo : dockerRepos) {
            //do the logic for checking
            try {
                checkForNewVersion(dockerRepo);
                System.out.println();
            } catch (DockerhubException e) {
                //ToDo log
                System.out.println("not valid  thing");
            }
            //
        }
    }

    private void checkForNewVersion(DockerRepo dockerRepo) throws DockerhubException {
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
                    dockerImageRepository.save(dockerImage);
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

                    //ToDo send it
                        hookMessageSender.send(dockerImage);
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
