package basilisk.hooksCheckingService.config;

import basilisk.hooksCheckingService.web.proxies.DockerHubRestProxy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Fakhr Shaheen
 */

@Configuration
@Scope("prototype")
public class ProxiesConfig {

    @Bean
    public DockerHubRestProxy dockerHubRestProxy(){
        return new DockerHubRestProxy(new RestTemplateBuilder());
    }
}
