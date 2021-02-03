package basilisk.hooksCheckingService.web.services.git;

import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class GitPullRequestCheckingService extends GitCheckingService {


    public GitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByType(GitType.pull_request);
    }


    @Override
    public void checkForNewVersion(GitRepo gitRepo) throws IOException {
        GHRepository repo = getRepoFromGH(gitRepo);

        List<GHPullRequest> pullRequests = repo.getPullRequests(GHIssueState.OPEN);
        Iterator<GHPullRequest> iterator = pullRequests.iterator();
        while (iterator.hasNext()) {
            GHPullRequest pullRequest = iterator.next();
            pullRequest.getUpdatedAt();

        }
    }
}

