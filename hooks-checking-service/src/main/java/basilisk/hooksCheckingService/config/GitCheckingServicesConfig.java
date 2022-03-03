package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.services.checkingServices.git.GitBranchCheckingService;
import basilisk.hooksCheckingService.services.checkingServices.git.GitCheckingService;
import basilisk.hooksCheckingService.services.checkingServices.git.GitPullRequestCheckingService;
import basilisk.hooksCheckingService.services.checkingServices.git.GitReleaseCheckingService;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GitCheckingServicesConfig {

    @Bean
    public GitCheckingService gitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        return new GitPullRequestCheckingService(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Bean
    public GitCheckingService gitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        return new GitReleaseCheckingService(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Bean
    public GitCheckingService gitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        return new GitBranchCheckingService(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

}
