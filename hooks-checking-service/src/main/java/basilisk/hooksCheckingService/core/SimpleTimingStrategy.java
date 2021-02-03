package basilisk.hooksCheckingService.core;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Fakhr Shaheen
 */

@Component
public class SimpleTimingStrategy implements TimingStrategy{
    @Override
    public void sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("hi");
    }
}
