package basilisk.hooksCheckingService.web.proxies;

import basilisk.hooksCheckingService.web.proxies.docker.DockerHubRestProxy;
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
    void getMySqlTag() {
        var result=dockerHubRestProxy.getTags("library","mysql");
        System.out.println(result.toString());
    }

    @Test
    void getTentrisServerTag() {
        var result=dockerHubRestProxy.getTags("dicegroup","tentris_server");
        System.out.println(result.toString());
    }


}