package basilisk.hooksCheckingService.web.services.continuesCheckingServices;

import basilisk.hooksCheckingService.core.TimingStrategy;
import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
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
public class ContinuesCheckingServiceImpl implements ContinuesCheckingService{

    public ContinuesCheckingServiceImpl(CheckingService checkingService, TimingStrategy timingStrategy) {
        this.checkingService = checkingService;
        this.timingStrategy = timingStrategy;
    }

    CheckingService checkingService;
    @Setter
    @Getter
    TimingStrategy timingStrategy;
    boolean is_running;

    @Override
    @Async
    public void start() throws InterruptedException {
        is_running=true;
        while (is_running)
        {
            timingStrategy.sleep();
            checkingService.performChecking();

        }
    }

    @Override
    public synchronized void stop() {
        is_running=false;
    }

    @Override
    public synchronized boolean isRunning() {
        return is_running;
    }
}
