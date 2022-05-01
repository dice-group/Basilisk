package org.dicegroup.basilisk.hooksCheckingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@EnableAsync
public class HooksCheckingServiceApplication {

    public static void main(String[] args)  {
        SpringApplication.run(HooksCheckingServiceApplication.class, args);
    }

}
