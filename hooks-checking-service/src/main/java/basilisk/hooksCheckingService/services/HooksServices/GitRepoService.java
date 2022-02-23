package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.model.git.GitRepoType;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<GitRepo> findAllRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAll().iterator());
    }

    public List<GitRepo> findAllGitReleaseRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAllByRepoType(GitRepoType.RELEASE).iterator());
    }

    public List<GitRepo> findAllGitPullRequestRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAllByRepoType(GitRepoType.PULL_REQUEST).iterator());
    }

    public List<GitRepo> findAllGitBranchRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAllByRepoType(GitRepoType.BRANCH).iterator());
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
