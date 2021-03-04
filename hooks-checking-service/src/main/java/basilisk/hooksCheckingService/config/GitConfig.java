package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.messaging.MessagingHandler;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.git.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fakhr Shaheen
 */

@Configuration
public class GitConfig {


    @Bean
    public GitCheckingServiceFactory gitCheckingServiceFactory(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessagingHandler hookMessageSender)
    {
        return new GitCheckingServiceFactory(gitRepoRepository,gitHookRepository,hookMessageSender);
    }

    @Bean
    public GitCheckingService gitPullRequestCheckingService(GitCheckingServiceFactory gitCheckingServiceFactory)
    {
        return gitCheckingServiceFactory.createGitCheckingService("pull");
    }

    @Bean
    public GitCheckingService gitReleaseCheckingService(GitCheckingServiceFactory gitCheckingServiceFactory)
    {
        return gitCheckingServiceFactory.createGitCheckingService("release");
    }

    @Bean
    public GitCheckingService gitBranchCheckingService(GitCheckingServiceFactory gitCheckingServiceFactory)
    {
        return gitCheckingServiceFactory.createGitCheckingService("branch");
    }

}
