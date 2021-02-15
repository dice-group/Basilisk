package basilisk.hooksCheckingService.web.services.continuesCheckingServices;

import basilisk.hooksCheckingService.core.TimingStrategy;
import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.docker.DockerhubCheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.git.GitBranchCheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.git.GitPullRequestCheckingService;
import basilisk.hooksCheckingService.web.services.checkingServices.git.GitReleaseCheckingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Fakhr Shaheen
 */

@Component
public class ContinuesCheckingServiceImpl implements ContinuesCheckingService {

    public ContinuesCheckingServiceImpl(GitBranchCheckingService gitBranchCheckingService
            , GitReleaseCheckingService gitReleaseCheckingService
            , GitPullRequestCheckingService gitPullRequestCheckingService
            , DockerhubCheckingService dockerhubCheckingService
            , TimingStrategy timingStrategy) {

        this.gitBranchCheckingService = gitBranchCheckingService;
        this.gitReleaseCheckingService = gitReleaseCheckingService;
        this.gitPullRequestCheckingService = gitPullRequestCheckingService;
        this.dockerhubCheckingService=dockerhubCheckingService;
        this.timingStrategy = timingStrategy;
    }

    GitBranchCheckingService gitBranchCheckingService;
    GitReleaseCheckingService gitReleaseCheckingService;
    GitPullRequestCheckingService gitPullRequestCheckingService;
    DockerhubCheckingService dockerhubCheckingService;
    @Setter
    @Getter
    TimingStrategy timingStrategy;
    boolean is_running;

    @Override
    @Async
    public void start() throws InterruptedException {
        is_running = true;
        while (is_running) {
            timingStrategy.sleep();
            gitBranchCheckingService.performChecking();
            gitReleaseCheckingService.performChecking();
            gitPullRequestCheckingService.performChecking();
            dockerhubCheckingService.performChecking();

        }
    }

    @Override
    public synchronized void stop() {
        is_running = false;
    }

    @Override
    public synchronized boolean isRunning() {
        return is_running;
    }
}
