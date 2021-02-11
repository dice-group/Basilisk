package basilisk.hooksCheckingService.web.services.checkingServices.docker;

import basilisk.hooksCheckingService.core.exception.DockerhubException;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.repositories.DockeHookRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

import java.util.Iterator;

/**
 * @author Fakhr Shaheen
 */
public class DockerhubCheckingService implements CheckingService {


    DockeHookRepository dockeHookRepository;
    DockerRepoRepository dockerRepoRepository;
    @Override
    public void performChecking() {
        //get the dockerhub repos
        Iterable<DockerRepo> dockerRepos = dockerRepoRepository.findAll();
        //go through them
        Iterator<DockerRepo> repoIterator=dockerRepos.iterator();
        while (repoIterator.hasNext())
        {
            DockerRepo dockerRepo =repoIterator.next();
            //do the logic for checking
            try {
                checkForNewVersion(dockerRepo);
            }
            catch (DockerhubException e)
            {
                //ToDo log
                System.out.println("not valid git thing");
            }
            //
        }
    }

    private void checkForNewVersion(DockerRepo dockerRepo) throws DockerhubException
    {
        DefaultDockerClientConfig.Builder config
                = DefaultDockerClientConfig.createDefaultConfigBuilder();
        DockerClient dockerClient = DockerClientBuilder
                .getInstance(config)
                .build();
        var r =dockerClient.listImagesCmd().withImageNameFilter(dockerRepo.getRepoName()).exec();
        r.get(0).getRepoTags();
        var images = dockerClient.listImagesCmd().exec();
    }
}
