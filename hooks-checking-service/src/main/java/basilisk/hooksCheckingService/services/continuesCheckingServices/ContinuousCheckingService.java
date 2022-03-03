package basilisk.hooksCheckingService.services.continuesCheckingServices;

import basilisk.hooksCheckingService.core.TimingStrategy;
import basilisk.hooksCheckingService.services.checkingServices.docker.DockerhubCheckingService;
import basilisk.hooksCheckingService.services.checkingServices.git.GitCheckingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class ContinuousCheckingService {

    GitCheckingService gitBranchCheckingService;
    GitCheckingService gitReleaseCheckingService;
    GitCheckingService gitPullRequestCheckingService;
    DockerhubCheckingService dockerhubCheckingService;
    @Setter
    @Getter
    TimingStrategy timingStrategy;
    boolean is_running;

    public ContinuousCheckingService(GitCheckingService gitBranchCheckingService
            , GitCheckingService gitReleaseCheckingService
            , GitCheckingService gitPullRequestCheckingService
            , DockerhubCheckingService dockerhubCheckingService
            , TimingStrategy timingStrategy) {

        this.gitBranchCheckingService = gitBranchCheckingService;
        this.gitReleaseCheckingService = gitReleaseCheckingService;
        this.gitPullRequestCheckingService = gitPullRequestCheckingService;
        this.dockerhubCheckingService = dockerhubCheckingService;
        this.timingStrategy = timingStrategy;
    }

    @Async
    public void start() throws InterruptedException {
        this.is_running = true;
        while (this.is_running) {
            this.gitBranchCheckingService.performChecking();
            this.gitReleaseCheckingService.performChecking();
            this.gitPullRequestCheckingService.performChecking();
            this.dockerhubCheckingService.performChecking();

            this.timingStrategy.sleep();
        }
    }

    public synchronized void stop() {
        this.is_running = false;
    }

    public synchronized boolean isRunning() {
        return this.is_running;
    }
}
