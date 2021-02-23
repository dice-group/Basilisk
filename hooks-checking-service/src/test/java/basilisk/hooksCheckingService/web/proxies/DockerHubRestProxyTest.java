package basilisk.hooksCheckingService.web.proxies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Fakhr Shaheen
 */

@SpringBootTest
class DockerHubRestProxyTest {


    @Autowired
    DockerHubRestProxy dockerHubRestProxy;

    @Test
    void getMySqltag() {
        var result=dockerHubRestProxy.getTages("library","mysql");
        System.out.println(result.getDockerTages().toString());
    }

    @Test
    void getTentrisServertag() {
        var result=dockerHubRestProxy.getTages("dicegroup","tentris_server");
        System.out.println(result.getDockerTages().toString());
    }


}