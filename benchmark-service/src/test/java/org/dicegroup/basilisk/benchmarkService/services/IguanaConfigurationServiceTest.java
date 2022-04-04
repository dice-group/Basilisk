package org.dicegroup.basilisk.benchmarkService.services;

import org.dicegroup.basilisk.benchmarkService.domain.Iguana.Dataset;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.storage.Storage;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.storage.TriplestoreStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
class IguanaConfigurationServiceTest {

    IguanaConfigurationService iguanaConfigurationService;


    @BeforeEach
    public void setUp() {
        iguanaConfigurationService = new IguanaConfigurationService();
    }

    @Test
    void shouldCreateDefaultIguanaConfiguration() {

        IguanaConnection connection = IguanaConnection.builder().name("connection1")
                .endpoint("https://test.com").build();
        List<Dataset> datasets = List.of(new Dataset("name", "filename"));
        List<Storage> storages = List.of(new TriplestoreStorage("x", "y"));

//        IguanaConfiguration iguanaConfiguration=iguanaConfigurationService.createDefaultIguanaConfiguration(connection,datasets,storages,"queryfile");
//        Assert.assertNotNull(iguanaConfiguration.getIguanaConnections());
//        Assert.assertNotNull(iguanaConfiguration.getIguanaTasks());
//        Assert.assertNotNull(iguanaConfiguration.getDatasets());
//        Assert.assertNotNull(iguanaConfiguration.getStorages());
//        Assert.assertNotNull(iguanaConfiguration.getIguanaMetrics());
    }
}