package basilisk.hooksCheckingService.web.services.continuesCheckingServices;

import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

/**
 * @author Fakhr Shaheen
 */
public interface ContinuesCheckingService {

    @Async
    public void start() throws InterruptedException;

    public void stop();

    public boolean isRunning();
}
