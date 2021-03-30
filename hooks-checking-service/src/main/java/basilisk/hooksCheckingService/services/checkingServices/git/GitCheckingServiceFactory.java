package basilisk.hooksCheckingService.services.checkingServices.git;

import basilisk.hooksCheckingService.web.messaging.MessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;

/**
 * @author Fakhr Shaheen
 */
public class GitCheckingServiceFactory {

    private  GitHookRepository gitHookRepository;
    private  GitRepoRepository gitRepoRepository;
    private MessageSender hookMessageSender;

    public GitCheckingServiceFactory(GitRepoRepository gitRepoRepository,GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        this.gitHookRepository = gitHookRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.hookMessageSender = hookMessageSender;
    }

    public GitCheckingService createGitCheckingService(String type)
    {
        switch (type)
        {
            case "pull":
                return new GitPullRequestCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
            case "release":
                return new GitReleaseCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
            case "branch":
                return new GitBranchCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
            default:
                return new GitBranchCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
        }
    }
}
