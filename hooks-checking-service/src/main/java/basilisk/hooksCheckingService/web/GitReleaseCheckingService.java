package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.HubRepos.GitHubRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.kohsuke.github.*;


import java.io.IOException;

public class GitReleaseCheckingService extends GitCheckingService{

    GitHubRepo gitRepo;

    public GitReleaseCheckingService(GitHubRepo gitRepo, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitHookRepository, hookMessageSender);
        this.gitRepo=gitRepo;
    }


    @Override
    public void checkForNewVersion() throws IOException {
        //get the repo from github
        GHRepository repo = getRepoFromGH(gitRepo);

        //get latest release
        GHRelease release=repo.getLatestRelease();




    }

}
