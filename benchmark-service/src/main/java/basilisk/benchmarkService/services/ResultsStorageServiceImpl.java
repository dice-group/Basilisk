package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.Iguana.storage.SecuredTripleStoreStorage;
import basilisk.benchmarkService.domain.Iguana.storage.TriplestoreStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Fakhr Shaheen
 */

@Service
public class ResultsStorageServiceImpl implements ResultsStorageService{

    @Value("${jobs.benchmark.iguana.resultStorage.endpoint}")
    private String endpoint;

    @Value("${jobs.benchmark.iguana.resultStorage.updateEndpoint}")
    private String updateEndpoint;

    @Value("${jobs.benchmark.iguana.resultStorage.user}")
    private String username;

    @Value("${jobs.benchmark.iguana.resultStorage.password}")
    private String password;


    @Override
    public TriplestoreStorage getDefaultBenchmarkStorage() {
        TriplestoreStorage triplestoreStorage;
        if(username.isEmpty())
        {
            triplestoreStorage=new TriplestoreStorage(endpoint,updateEndpoint);
        }
        else
        {
            triplestoreStorage=new SecuredTripleStoreStorage(endpoint,updateEndpoint,username,password);
        }
        return triplestoreStorage;
    }
}
