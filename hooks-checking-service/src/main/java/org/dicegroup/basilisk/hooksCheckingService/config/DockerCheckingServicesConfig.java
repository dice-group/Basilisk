package org.dicegroup.basilisk.hooksCheckingService.config;

import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerImageRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.dicegroup.basilisk.hooksCheckingService.repositories.DockerTagRepository;
import org.dicegroup.basilisk.hooksCheckingService.services.checkingServices.docker.DockerhubCheckingService;
import org.dicegroup.basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DockerCheckingServicesConfig {

    @Bean
    public DockerhubCheckingService dockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, MessageSender hookMessageSender)
    {
        return new DockerhubCheckingService(dockerRepoRepository,dockerImageRepository,dockerTagRepository,hookMessageSender);
    }

}
