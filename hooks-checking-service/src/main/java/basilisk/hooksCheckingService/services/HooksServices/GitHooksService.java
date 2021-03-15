package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.springframework.stereotype.Service;


/**
 * @author Fakhr Shaheen
 */

@Service
public class GitHooksService {

    private GitRepoRepository gitRepoRepository;


    public Iterable<GitRepo> findAllGitReleaseRepos()
    {
        return gitRepoRepository.findAllByType(GitType.release);
    }

    public Iterable<GitRepo> findAllGitPullRequestRepos()
    {
        return gitRepoRepository.findAllByType(GitType.pull_request);
    }

    public Iterable<GitRepo> findAllGitBranchRepos()
    {
        return gitRepoRepository.findAllByType(GitType.branch);
    }

    public GitHooksService(GitRepoRepository gitRepoRepository) {
        this.gitRepoRepository = gitRepoRepository;
    }

    public void addGitRepoForRelease(GitRepo gitRepo)
    {
        GitRepo receivedGitRepo=gitRepo;
        receivedGitRepo.setType(GitType.release);
        gitRepoRepository.save(receivedGitRepo);
    }

    public void addGitRepoForPullRequest(GitRepo gitRepo)
    {
        GitRepo receivedGitRepo=gitRepo;
        receivedGitRepo.setType(GitType.pull_request);
        gitRepoRepository.save(receivedGitRepo);
    }

    public void addGitRepoForPullBranch(GitRepo gitBranchRepo )
    {
        GitRepo receivedGitRepo=gitBranchRepo;
        receivedGitRepo.setType(GitType.branch);
        gitRepoRepository.save(receivedGitRepo);
    }
}
