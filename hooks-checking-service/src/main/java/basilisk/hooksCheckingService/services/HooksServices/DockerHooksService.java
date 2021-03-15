package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.springframework.stereotype.Service;

/**
 * @author Fakhr Shaheen
 */
@Service
public class DockerHooksService {

    private DockerRepoRepository dockerRepoRepository;

    public DockerHooksService(DockerRepoRepository dockerRepoRepository) {
        this.dockerRepoRepository = dockerRepoRepository;
    }

    public Iterable<DockerRepo> findAllDockerRepos() {
        return dockerRepoRepository.findAll();
    }

    public void addDockerRepo(DockerRepo dockerRepo)
    {
        dockerRepoRepository.save(dockerRepo);
    }


}
