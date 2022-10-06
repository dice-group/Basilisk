package org.dicegroup.basilisk.jobsManagingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@EnableAsync
public class JobsManagingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsManagingServiceApplication.class, args);
    }

}
