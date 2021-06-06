package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.benchamrking.BenchmarkJob;


/**
 * @author Fakhr Shaheen
 */
public interface BenchamrkingOrganizerService {

    void startBenchmark(BenchmarkJob benchmarkJob);

    void abortBenchmark(Long jobId);
}
