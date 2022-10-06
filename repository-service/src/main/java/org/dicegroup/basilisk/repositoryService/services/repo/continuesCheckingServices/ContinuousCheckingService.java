package org.dicegroup.basilisk.repositoryService.services.repo.continuesCheckingServices;

import lombok.Getter;
import lombok.Setter;
import org.dicegroup.basilisk.repositoryService.core.TimingStrategy;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.DockerhubCheckingService;
import org.dicegroup.basilisk.repositoryService.services.repo.checkingService.git.GitCheckingService;
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
    private boolean isRunning;

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
        this.isRunning = true;
        while (this.isRunning) {
            this.gitBranchCheckingService.performChecking();
            this.gitReleaseCheckingService.performChecking();
            this.gitPullRequestCheckingService.performChecking();
            this.dockerhubCheckingService.performChecking();

            this.timingStrategy.sleep();
        }
    }

    public synchronized void stop() {
        this.isRunning = false;
    }

    public synchronized boolean isRunning() {
        return this.isRunning;
    }
}
