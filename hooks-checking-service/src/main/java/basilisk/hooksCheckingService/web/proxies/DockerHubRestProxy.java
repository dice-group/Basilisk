package basilisk.hooksCheckingService.web.proxies;

import basilisk.hooksCheckingService.domain.docker.api.DockerTagApiCall;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Fakhr Shaheen
 */

@Component
@ConfigurationProperties(value = "proxies.docker")
public class DockerHubRestProxy {


        public DockerHubRestProxy(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private final String apihost="https://registry.hub.docker.com";
    private final RestTemplate restTemplate;

    public DockerTagApiCall getTages(String ownerName,String repoName) {
        String fooResourceUrl
                = apihost + "/v2/repositories/"+ownerName+"/" + repoName + "/tags";
        DockerTagApiCall response
                = restTemplate.getForObject(fooResourceUrl, DockerTagApiCall.class);
        return response;
    }

}
