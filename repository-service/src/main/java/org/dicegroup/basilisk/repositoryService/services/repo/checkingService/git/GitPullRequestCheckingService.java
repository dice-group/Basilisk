package org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git;

import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.repositoryService.core.exception.GithubException;
import org.dicegroup.basilisk.repositoryService.model.repo.git.GitRepo;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitHookRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitRepoRepository;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.util.List;


public class GitPullRequestCheckingService extends GitCheckingService {


    public GitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, BenchmarkJobService benchmarkJobService) {
        super(gitRepoRepository, gitHookRepository, benchmarkJobService);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return this.gitRepoRepository.findAllByGitRepoType(GitRepoType.PULL_REQUEST);
    }

    @Override
    public void checkRepo(GitRepo gitRepo) throws GithubException {
        try {

            GHRepository repo = getRepoFromGitHub(gitRepo);

            List<GHPullRequest> pullRequests = repo.getPullRequests(GHIssueState.OPEN);
            for (GHPullRequest pullRequest : pullRequests) {

                checkHooks(gitRepo, repo, pullRequest.getHead().getSha());

            }
        } catch (Exception e) {
            throw new GithubException();
        }
    }
}

