package basilisk.hooksCheckingService.web.proxies;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Fakhr Shaheen
 */
public class DockerHubRestProxy {

    public static String getTages(String repoName)
    {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://registry.hub.docker.com/v2/repositories/library/mysql/tags/";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.toString();
    }

}
