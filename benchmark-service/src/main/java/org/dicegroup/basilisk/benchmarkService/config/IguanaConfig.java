package org.dicegroup.basilisk.benchmarkService.config;

import org.aksw.iguana.cc.controller.MainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IguanaConfig {

    @Bean
    public MainController iguanaController() {
        return new MainController();
    }

}
