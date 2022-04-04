package org.dicegroup.basilisk.hooksCheckingService.web.proxies.docker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */


public class DockerHubRestProxy {

    private final Logger logger = LoggerFactory.getLogger(DockerHubRestProxy.class);
    private final RestTemplate restTemplate;
    @Value("${proxies.docker.registryUrl}")
    private String dockerRegistryUrl;

    public DockerHubRestProxy(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<DockerApiTag> getTags(String ownerName, String repoName) {
        String resourceUrl = this.dockerRegistryUrl + "/v2/repositories/" + ownerName + "/" + repoName + "/tags";

        ResponseEntity<DockerTagApiCall> responseEntity = this.restTemplate.getForEntity(resourceUrl, DockerTagApiCall.class);
        HttpHeaders headers = responseEntity.getHeaders();

        this.logger.info("Docker remaining rate: {}", headers.getFirst("X-RateLimit-Remaining"));

        DockerTagApiCall response = responseEntity.getBody();

        assert response != null;
        List<DockerApiTag> tags = new ArrayList<>(response.getDockerTags());
        while (true) {
            assert response != null;
            if (response.getNext() == null) break;
            response = restTemplate.getForObject(response.getNext(), DockerTagApiCall.class);
            if (response != null) {
                tags.addAll(response.getDockerTags());
            }
        }

        return tags;
    }
}
