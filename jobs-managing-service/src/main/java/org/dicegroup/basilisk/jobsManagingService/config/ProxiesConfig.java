package org.dicegroup.basilisk.jobsManagingService.config;

import org.dicegroup.basilisk.jobsManagingService.web.proxies.docker.DockerHubRestProxy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
@Scope("prototype")
public class ProxiesConfig {

    @Bean
    public DockerHubRestProxy dockerHubRestProxy(){
        return new DockerHubRestProxy(new RestTemplateBuilder());
    }
}
