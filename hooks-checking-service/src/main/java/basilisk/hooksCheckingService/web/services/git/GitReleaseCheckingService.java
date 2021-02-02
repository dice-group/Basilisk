package basilisk.hooksCheckingService.web.services.git;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;


import java.io.IOException;

public class GitReleaseCheckingService extends GitCheckingService{


    public GitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    public void checkForNewVersion(GitRepo gitRepo) throws IOException {
//        //get the repo from github
//        GHRepository repo = getRepoFromGH(gitRepo);
//
//        //get latest release
//        GHRelease release=repo.getLatestRelease();




    }

}
