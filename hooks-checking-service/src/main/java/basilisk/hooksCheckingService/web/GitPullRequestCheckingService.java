package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.HooksRepos.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GitPullRequestCheckingService extends GitCheckingService {

    GitRepo gitRepo;

    public GitPullRequestCheckingService(GitRepo gitRepo, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitHookRepository, hookMessageSender);
        this.gitRepo = gitRepo;
    }

    @Override
    public void checkForNewVersion() throws IOException {
        GHRepository repo = getRepoFromGH(gitRepo);

        List<GHPullRequest> pullRequests = repo.getPullRequests(GHIssueState.OPEN);
        Iterator<GHPullRequest> iterator = pullRequests.iterator();
        while (iterator.hasNext()) {
            GHPullRequest pullRequest = iterator.next();
            pullRequest.getUpdatedAt();
        }


    }

}
