package basilisk.hooksCheckingService.services.continuesCheckingServices;

import basilisk.hooksCheckingService.core.TimingStrategy;
import basilisk.hooksCheckingService.services.checkingServices.docker.DockerhubCheckingService;
import basilisk.hooksCheckingService.services.checkingServices.git.GitCheckingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Fakhr Shaheen, Fabian Rensing
 */

@Service
public class ContinuousCheckingServiceImpl implements ContinuousCheckingService {

    public ContinuousCheckingServiceImpl(GitCheckingService gitBranchCheckingService
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

    GitCheckingService gitBranchCheckingService;
    GitCheckingService gitReleaseCheckingService;
    GitCheckingService gitPullRequestCheckingService;
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
