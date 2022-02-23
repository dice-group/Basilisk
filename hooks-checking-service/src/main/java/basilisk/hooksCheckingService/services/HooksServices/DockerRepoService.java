package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.model.docker.DockerRepo;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DockerRepoService {

    private final Logger logger = LoggerFactory.getLogger(DockerRepoService.class);

    private final DockerRepoRepository dockerRepoRepository;

    public DockerRepoService(DockerRepoRepository dockerRepoRepository) {
        this.dockerRepoRepository = dockerRepoRepository;
    }

    public List<DockerRepo> findAllDockerRepos() {
        return IteratorUtils.toList(this.dockerRepoRepository.findAll().iterator());
    }

    public Optional<DockerRepo> findRepo(long id) {
        return this.dockerRepoRepository.findById(id);
    }

    public void addDockerRepo(DockerRepo dockerRepo) {
        DockerRepo createdRepo = this.dockerRepoRepository.save(dockerRepo);
        logger.info("Added Docker repo with id: {}", createdRepo.getId());
        this.dockerRepoRepository.save(dockerRepo);
    }

    public void deleteRepo(DockerRepo repo) {
        Optional<DockerRepo> ownRepo = findRepo(repo.getId());
        if (ownRepo.isPresent()) {
            this.dockerRepoRepository.delete(ownRepo.get());
            logger.info("Deleted Docker repo with ID {}", repo.getId());
        } else {
            logger.error("Repo was not found! ID: {}", repo.getId());
        }
    }
}
