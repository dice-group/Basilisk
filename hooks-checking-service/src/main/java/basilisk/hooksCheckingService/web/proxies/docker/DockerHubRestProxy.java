package basilisk.hooksCheckingService.web.proxies.docker;


import basilisk.hooksCheckingService.web.proxies.docker.DockerApiTag;
import basilisk.hooksCheckingService.web.proxies.docker.DockerTagApiCall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */


public class DockerHubRestProxy {


    public DockerHubRestProxy(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Value("${proxies.docker.apihost}")
    private String apihost;
    private final RestTemplate restTemplate;

    public List<DockerApiTag> getTags(String ownerName, String repoName) {
        List<DockerApiTag> tags = new ArrayList<>();
        String fooResourceUrl
                = apihost + "/v2/repositories/" + ownerName + "/" + repoName + "/tags";
        DockerTagApiCall response
                = restTemplate.getForObject(fooResourceUrl, DockerTagApiCall.class);
        tags.addAll(response.getDockerTages());
        while (response.getNext()!=null) {
            response = restTemplate.getForObject(response.getNext(), DockerTagApiCall.class);
            tags.addAll(response.getDockerTages());
        }

        return tags;
    }

}
