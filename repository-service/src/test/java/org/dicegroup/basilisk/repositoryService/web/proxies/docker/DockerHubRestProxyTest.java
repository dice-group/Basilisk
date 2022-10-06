package org.dicegroup.basilisk.repositoryService.web.proxies.docker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DockerHubRestProxyTest {


    @Autowired
    DockerHubRestProxy dockerHubRestProxy;

    @Test
    void getMySqlTag() {
        var result = dockerHubRestProxy.getTags("library", "mysql");
        System.out.println(result.toString());
    }

    @Test
    void getTentrisServerTag() {
        var result = dockerHubRestProxy.getTags("dicegroup", "tentris_server");
        System.out.println(result.toString());
    }


}