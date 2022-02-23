package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.services.checkingServices.git.GitCheckingService;
import basilisk.hooksCheckingService.services.checkingServices.git.GitCheckingServiceFactory;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GitCheckingServicesConfig {


    @Bean
    public GitCheckingServiceFactory gitCheckingServiceFactory(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender)
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
