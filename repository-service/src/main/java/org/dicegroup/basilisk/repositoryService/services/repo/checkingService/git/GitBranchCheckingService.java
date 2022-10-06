package org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git;


import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.repositoryService.core.exception.GithubException;
import org.dicegroup.basilisk.repositoryService.model.repo.git.GitRepo;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitHookRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitRepoRepository;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;
import org.springframework.messaging.MessagingException;


public class GitBranchCheckingService extends GitCheckingService {

    public GitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, BenchmarkJobService benchmarkJobService) {
        super(gitRepoRepository, gitHookRepository, benchmarkJobService);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByGitRepoType(GitRepoType.BRANCH);
    }


    @Override
    protected void checkRepo(GitRepo gitRepo) throws GithubException {

        try {

            GHRepository repo = getRepoFromGitHub(gitRepo);

            //get latest commit on the branch
            GHBranch branch = repo.getBranch(gitRepo.getBranchName());

            //  check whether the hook is already saved
            checkHooks(gitRepo, repo, branch.getSHA1());

        } catch (MessagingException e) {
            // TODO
            throw e;
        } catch (Exception e) {
            throw new GithubException();
        }
    }

}
