package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.Iguana.storage.TriplestoreStorage;

/**
 * @author Fakhr Shaheen
 */
public interface ResultsStorageService {

    TriplestoreStorage getDefaultBenchmarkStorage();
}
