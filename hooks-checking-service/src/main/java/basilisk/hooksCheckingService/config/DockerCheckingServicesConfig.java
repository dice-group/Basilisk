package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.DockerTagRepository;
import basilisk.hooksCheckingService.services.checkingServices.docker.DockerhubCheckingService;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
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
