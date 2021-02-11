package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.DockeHookRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.docker.DockerhubCheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.git.GitBranchCheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.git.GitPullRequestCheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.git.GitReleaseCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class config {

    @Bean
    public GitPullRequestCheckingService gitPullRequestCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender)
    {
        return new GitPullRequestCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
    }

    @Bean
    public GitReleaseCheckingService gitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender)
    {
        return new GitReleaseCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
    }

    @Bean
    public GitBranchCheckingService gitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender)
    {
        return new GitBranchCheckingService(gitRepoRepository,gitHookRepository,hookMessageSender);
    }

    @Bean
    public DockerhubCheckingService dockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockeHookRepository dockeHookRepository, HookMessageSender hookMessageSender)
    {
        return new DockerhubCheckingService(dockerRepoRepository,dockeHookRepository,hookMessageSender);
    }
}
