package org.dicegroup.basilisk.hooksCheckingService.services.HooksServices;

import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import org.dicegroup.basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GitRepoService {

    private final Logger logger = LoggerFactory.getLogger(GitRepoService.class);

    private final GitRepoRepository gitRepoRepository;


    public GitRepoService(GitRepoRepository gitRepoRepository) {
        this.gitRepoRepository = gitRepoRepository;
    }

    public Optional<GitRepo> findRepo(long id) {
        return this.gitRepoRepository.findById(id);
    }

    public void addRepo(GitRepo gitRepo) {
        GitRepo createdRepo = this.gitRepoRepository.save(gitRepo);
        logger.info("Added Git repo with id: {}", createdRepo.getId());
    }

    public void deleteRepo(GitRepo repo) {
        Optional<GitRepo> ownRepo = findRepo(repo.getId());
        if (ownRepo.isPresent()) {
            this.gitRepoRepository.delete(ownRepo.get());
            logger.info("Deleted Git repo with ID {}", repo.getId());
        } else {
            logger.error("Repo was not found! ID: {}", repo.getId());
        }
    }
}
