package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.HooksRepos.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public abstract class GitCheckingService implements HookCheckingService{


    protected final GitHookRepository gitHookRepository;
    protected final HookMessageSender hookMessageSender;

    public GitCheckingService(GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        this.gitHookRepository = gitHookRepository;
        this.hookMessageSender = hookMessageSender;
    }

    public void performChecking() throws IOException {
        checkForNewVersion();
    }


    protected abstract void checkForNewVersion() throws IOException;


    protected GHRepository getRepoFromGH(GitRepo gitRepo) throws IOException {
        GitHub github;
        if (gitRepo.isPrivate())
            github = new GitHubBuilder().withOAuthToken(gitRepo.getOAuthToken()).build();
        else
            github =GitHub.connectAnonymously();

        GHRepository repo = github.getUser(gitRepo.getRepoOwner()).getRepository(gitRepo.getRepoName());

        return repo;
    }



}
