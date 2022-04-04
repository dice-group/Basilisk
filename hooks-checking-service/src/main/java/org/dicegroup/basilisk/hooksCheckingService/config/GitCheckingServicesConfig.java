package org.dicegroup.basilisk.hooksCheckingService.config;

import org.dicegroup.basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.git.GitBranchCheckingService;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.git.GitCheckingService;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.git.GitPullRequestCheckingService;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.git.GitReleaseCheckingService;
import org.dicegroup.basilisk.hooksCheckingService.web.messaging.MessageSender;
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
