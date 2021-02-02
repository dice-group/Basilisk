package basilisk.hooksCheckingService.web.Services;


import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.*;

import java.io.IOException;


public class GitBranchCheckingService extends GitCheckingService {


    public GitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected void checkForNewVersion() throws IOException {
//        GHRepository repo = getRepoFromGH(gitBranchRepo);
//
//        //get latest commit on the branch
//        GHBranch branch = repo.getBranch(gitBranchRepo.getBranchName());
//        GHCommit commit = repo.getCommit(branch.getSHA1());
        //check whether the hook is already saved
       // GitHook foundHook = gitHookRepository.findBySha1Hash(commit.getSHA1());
//        if(foundHook==null)
//        {
//            GitHook gitHook=new GitHook();
//            //save the hook
//            //gitHookRepository.addNewHook(gitHook);
//            //send the hook to the queue(or to the other service)
//            //hookMessageSender.sendHookMessage(gitHook);
//        }
//        else
//        {
//
//        }


    }

}
