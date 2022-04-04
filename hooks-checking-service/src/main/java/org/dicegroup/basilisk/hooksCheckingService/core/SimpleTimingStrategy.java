package org.dicegroup.basilisk.hooksCheckingService.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Fakhr Shaheen, Fabian Rensing
 */

@Component
public class SimpleTimingStrategy implements TimingStrategy {

    @Value("${core.timing.timeout}")
    private long timeout;

    @Override
    public void sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
