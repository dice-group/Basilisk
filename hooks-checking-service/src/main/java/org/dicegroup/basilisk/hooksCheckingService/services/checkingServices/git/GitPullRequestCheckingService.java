package org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.git;

import org.dicegroup.basilisk.hooksCheckingService.core.exception.GithubException;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepoType;
import org.dicegroup.basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.dicegroup.basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.util.List;


public class GitPullRequestCheckingService extends GitCheckingService {


    public GitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return this.gitRepoRepository.findAllByRepoType(GitRepoType.PULL_REQUEST);
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

