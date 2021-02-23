package basilisk.hooksCheckingService.web.services.checkingServices.docker;

import basilisk.hooksCheckingService.core.exception.DockerhubException;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;


import java.util.Iterator;

/**
 * @author Fakhr Shaheen
 */
public class DockerhubCheckingService implements CheckingService {


    DockerImageRepository dockerImageRepository;
    DockerRepoRepository dockerRepoRepository;
    HookMessageSender hookMessageSender;

    public DockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, HookMessageSender hookMessageSender) {
        this.dockerImageRepository = dockerImageRepository;
        this.dockerRepoRepository = dockerRepoRepository;
        this.hookMessageSender = hookMessageSender;
    }

    @Override
    public void performChecking() {
        //get the dockerhub repos
        Iterable<DockerRepo> dockerRepos = dockerRepoRepository.findAll();
        //go through them
        Iterator<DockerRepo> repoIterator = dockerRepos.iterator();
        while (repoIterator.hasNext()) {
            DockerRepo dockerRepo = repoIterator.next();
            //do the logic for checking
            try {
                var s=checkForNewVersion(dockerRepo);
                System.out.println();
            } catch (DockerhubException e) {
                //ToDo log
                System.out.println("not valid git thing");
            }
            //
        }
    }

    private String checkForNewVersion(DockerRepo dockerRepo) throws DockerhubException {
//        DockerHubRestProxy dockerHubRestProxy;
//         result=dockerHubRestProxy.getTages(dockerRepo.getRepoName());
//        return result;

        return "Hi";
    }
}
