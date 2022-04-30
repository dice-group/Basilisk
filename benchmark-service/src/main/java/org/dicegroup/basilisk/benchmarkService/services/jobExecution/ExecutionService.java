package org.dicegroup.basilisk.benchmarkService.services.jobExecution;

import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;

import java.io.IOException;

public interface ExecutionService {

    void executeBenchmarkJob(BenchmarkJob job) throws IOException, InterruptedException;

}
