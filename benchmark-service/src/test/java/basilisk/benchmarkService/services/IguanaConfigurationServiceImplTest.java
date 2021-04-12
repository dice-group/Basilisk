package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.Iguana.Dataset;
import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import basilisk.benchmarkService.domain.Iguana.storage.FileStorage;
import basilisk.benchmarkService.domain.Iguana.storage.Storage;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;


import java.util.List;

import static org.junit.Assert.assertNotNull;


/**
 * @author Fakhr Shaheen
 */

@SpringBootTest
class IguanaConfigurationServiceImplTest {

    @Autowired
    Environment env;

    IguanaConfigurationServiceImpl iguanaConfigurationService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        iguanaConfigurationService=new IguanaConfigurationServiceImpl();
    }

    @Test
    void createDefaultIguanaConfiguration() {
        assertNotNull (env);
        IguanaConnection connection= IguanaConnection.builder().name("conncection1")
                .endpoint("https://example.com").build();
        List<Dataset > datasets=List.of(new Dataset("name","filename"));
        List< Storage > storages=List.of(new FileStorage());


        IguanaConfiguration iguanaConfiguration=iguanaConfigurationService.createDefaultIguanaConfiguration(connection,datasets,storages);

        int x=2;
       /* assert (iguanaConfiguration.getIguanaConnections(),List.of(connection));*/
    }
}