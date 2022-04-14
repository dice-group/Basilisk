package org.dicegroup.basilisk.benchmarkService.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ResultsStorageService {

    @Value("${iguana.resultStorage.endpoint}")
    private String endpoint;

    @Value("${iguana.resultStorage.updateEndpoint}")
    private String updateEndpoint;

    @Value("${iguana.resultStorage.user}")
    private String username;

    @Value("${iguana.resultStorage.password}")
    private String password;


//    public TriplestoreStorage getDefaultBenchmarkStorage() {
//        TriplestoreStorage triplestoreStorage;
//        if (username.isEmpty()) {
//            triplestoreStorage = new TriplestoreStorage(endpoint, updateEndpoint);
//        } else {
//            triplestoreStorage = new SecuredTripleStoreStorage(endpoint, updateEndpoint, username, password);
//        }
//        return triplestoreStorage;
//    }
}
