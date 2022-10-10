package org.dicegroup.basilisk.repositoryService.config;


import org.dicegroup.basilisk.repositoryService.repositories.repo.GitHookRepository;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitRepoRepository;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git.GitBranchCheckingService;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git.GitCheckingService;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git.GitPullRequestCheckingService;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git.GitReleaseCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GitCheckingServicesConfig {

    @Bean
    public GitCheckingService gitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, BenchmarkJobService benchmarkJobService) {
        return new GitPullRequestCheckingService(gitRepoRepository, gitHookRepository, benchmarkJobService);
    }

    @Bean
    public GitCheckingService gitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, BenchmarkJobService benchmarkJobService) {
        return new GitReleaseCheckingService(gitRepoRepository, gitHookRepository, benchmarkJobService);
    }

    @Bean
    public GitCheckingService gitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, BenchmarkJobService benchmarkJobService) {
        return new GitBranchCheckingService(gitRepoRepository, gitHookRepository, benchmarkJobService);
    }

}
