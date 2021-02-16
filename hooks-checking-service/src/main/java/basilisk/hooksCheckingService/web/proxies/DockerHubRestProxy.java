package basilisk.hooksCheckingService.web.proxies;

import basilisk.hooksCheckingService.domain.docker.DockerCall;
import basilisk.hooksCheckingService.domain.docker.DockerTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Component
@ConfigurationProperties(value = "proxies.docker")
public class DockerHubRestProxy {

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }


        public DockerHubRestProxy(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private String apihost;
    private final RestTemplate restTemplate;

    public String getTages(String repoName) {
        String fooResourceUrl
                = apihost + "/v2/repositories/" + repoName + "/tags/";
        DockerCall response
                = restTemplate.getForObject(fooResourceUrl, DockerCall.class);
        return response.toString();
    }

}
