package basilisk.hooksCheckingService.services.checkingServices.git;

import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
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
        return gitRepoRepository.findAllByType(GitType.PULL_REQUEST);
    }


    @Override
    public void checkRepo(GitRepo gitRepo) throws GithubException {
        try {
            //ToDo : complete the logic for saving.

            GHRepository repo = getRepoFromGitHub(gitRepo);

            List<GHPullRequest> pullRequests = repo.getPullRequests(GHIssueState.OPEN);
            for (GHPullRequest pullRequest : pullRequests) {
                pullRequest.getUpdatedAt();
            }
        }
        catch (Exception e)
        {
            throw new GithubException();
        }
    }
}

