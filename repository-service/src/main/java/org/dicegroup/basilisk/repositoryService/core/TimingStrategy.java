package org.dicegroup.basilisk.repositoryService.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class TimingStrategy {

    @Value("${core.timing.timeout}")
    private long timeout;

    public void sleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
