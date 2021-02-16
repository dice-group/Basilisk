package basilisk.hooksCheckingService.web.proxies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Fakhr Shaheen
 */

@SpringBootTest
class DockerHubRestProxyTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DockerHubRestProxy dockerHubRestProxy;

    @Test
    void gettag()
    {
        String json=dockerHubRestProxy.getTages("dicegroup/tentris_server");
//        objectMapper.readValue(json,)
        System.out.println(json);
    }

}