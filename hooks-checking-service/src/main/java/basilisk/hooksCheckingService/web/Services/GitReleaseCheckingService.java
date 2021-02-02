package basilisk.hooksCheckingService.web.Services;

import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.*;


import java.io.IOException;

public class GitReleaseCheckingService extends GitCheckingService{


    public GitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    public void checkForNewVersion() throws IOException {
//        //get the repo from github
//        GHRepository repo = getRepoFromGH(gitRepo);
//
//        //get latest release
//        GHRelease release=repo.getLatestRelease();




    }

}
