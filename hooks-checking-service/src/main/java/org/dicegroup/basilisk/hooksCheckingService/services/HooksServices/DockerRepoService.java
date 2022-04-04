package org.dicegroup.basilisk.hooksCheckingService.services.HooksServices;

import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DockerRepoService {

    private final Logger logger = LoggerFactory.getLogger(DockerRepoService.class);

    private final DockerRepoRepository dockerRepoRepository;

    public DockerRepoService(DockerRepoRepository dockerRepoRepository) {
        this.dockerRepoRepository = dockerRepoRepository;
    }

    public Optional<DockerRepo> findRepo(long id) {
        return this.dockerRepoRepository.findById(id);
    }

    public void addRepo(DockerRepo dockerRepo) {
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
