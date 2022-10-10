package org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git;

import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.repositoryService.core.exception.GithubException;
import org.dicegroup.basilisk.repositoryService.model.repo.git.GitRepo;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitHookRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitRepoRepository;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;


public class GitReleaseCheckingService extends GitCheckingService {


    public GitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, BenchmarkJobService benchmarkJobService) {
        super(gitRepoRepository, gitHookRepository, benchmarkJobService);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return this.gitRepoRepository.findAllByGitRepoType(GitRepoType.RELEASE);
    }

    @Override
    public void checkRepo(GitRepo gitRepo) throws GithubException {
        try {

            //get the repo from github
            GHRepository repo = getRepoFromGitHub(gitRepo);

            //get latest release
            GHRelease release = repo.getLatestRelease();
            GHCommit commit = repo.getCommit(release.getTargetCommitish());

            //  check whether the hook is already saved
            checkHooks(gitRepo, repo, commit.getSHA1());

        } catch (Exception e) {
            throw new GithubException();
        }
    }

}
