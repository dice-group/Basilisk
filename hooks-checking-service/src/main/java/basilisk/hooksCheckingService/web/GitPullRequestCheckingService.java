package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.Hooks.Hook;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;

import java.io.IOException;

public class GitPullRequestCheckingService implements GitCheckingService {

    private GitPullRequestRepo gitRepo;



    private void checkPullRequest(int id) throws IOException {
        PullRequestService service = new PullRequestService();

        if (gitRepo.isPrivate())
            service.getClient().setCredentials(gitRepo.getRepoOwner(), gitRepo.getOAuthToken());
        else
            service.getClient().setUserAgent(gitRepo.getRepoOwner());

        RepositoryId repo = new RepositoryId(gitRepo.getRepoOwner(), gitRepo.getRepoName());
        PullRequest pullRequest = service.getPullRequest(repo, id);
        pullRequest.getUpdatedAt();

    }

    @Override
    public void checkForNewVersion() throws IOException {

        PullRequestService service = new PullRequestService();

        if (gitRepo.isPrivate())
            service.getClient().setCredentials(gitRepo.getRepoOwner(), gitRepo.getOAuthToken());
        else
            service.getClient().setUserAgent(gitRepo.getRepoOwner());

        RepositoryId repo = new RepositoryId(gitRepo.getRepoOwner(), gitRepo.getRepoName());
        service.getPullRequests(repo, gitRepo.getPullRequestState());
    }

    @Override
    public Hook getHook() {
        return null;
    }
}
