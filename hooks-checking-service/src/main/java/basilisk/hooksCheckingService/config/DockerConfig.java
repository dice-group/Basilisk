package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.messaging.MessagingHandler;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.repositories.DockerTagRepository;
import basilisk.hooksCheckingService.services.checkingServices.docker.DockerhubCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fakhr Shaheen
 */

@Configuration
public class DockerConfig {

    @Bean
    public DockerhubCheckingService dockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, DockerTagRepository dockerTagRepository, MessagingHandler hookMessageSender)
    {
        return new DockerhubCheckingService(dockerRepoRepository,dockerImageRepository,dockerTagRepository,hookMessageSender);
    }

}
