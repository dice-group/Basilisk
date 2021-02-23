package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.DockerImageRepository;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.docker.DockerhubCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fakhr Shaheen
 */

@Configuration
public class DockerConfig {

    @Bean
    public DockerhubCheckingService dockerhubCheckingService(DockerRepoRepository dockerRepoRepository, DockerImageRepository dockerImageRepository, HookMessageSender hookMessageSender)
    {
        return new DockerhubCheckingService(dockerRepoRepository,dockerImageRepository,hookMessageSender);
    }

}
