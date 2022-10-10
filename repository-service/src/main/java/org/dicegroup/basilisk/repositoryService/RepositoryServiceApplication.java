package org.dicegroup.basilisk.repositoryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@EnableAsync
public class RepositoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepositoryServiceApplication.class, args);
    }

}
