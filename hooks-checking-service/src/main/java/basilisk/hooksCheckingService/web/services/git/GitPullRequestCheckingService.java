package basilisk.hooksCheckingService.web.services.git;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class GitPullRequestCheckingService extends GitCheckingService {


    public GitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
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

