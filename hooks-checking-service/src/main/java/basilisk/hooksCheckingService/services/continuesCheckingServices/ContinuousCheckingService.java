package basilisk.hooksCheckingService.services.continuesCheckingServices;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Fakhr Shaheen
 */
public interface ContinuousCheckingService {

    @Async
    public void start() throws InterruptedException;

    public void stop();

    public boolean isRunning();
}
