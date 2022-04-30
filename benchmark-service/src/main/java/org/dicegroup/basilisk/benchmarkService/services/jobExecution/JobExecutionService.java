package org.dicegroup.basilisk.benchmarkService.services.jobExecution;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.GitBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.JobStatus;
import org.dicegroup.basilisk.benchmarkService.repositories.BenchmarkJobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JobExecutionService {

    private final BenchmarkJobRepository jobRepository;
    private final DockerJobExecutionService dockerExecutionService;
    private final GitJobExecutionService gitJobExecutionService;
    private boolean isRunning;
    @Value("${benchmark.startInterval}")
    private long startInterval;

    public JobExecutionService(BenchmarkJobRepository jobRepository, DockerJobExecutionService dockerExecutionService, GitJobExecutionService gitJobExecutionService) {
        this.jobRepository = jobRepository;
        this.dockerExecutionService = dockerExecutionService;
        this.gitJobExecutionService = gitJobExecutionService;
    }

    @Async
    public void start() throws InterruptedException, IOException {
        this.isRunning = true;
        while (this.isRunning) {
            List<BenchmarkJob> jobs = jobRepository.findAllByStatus(JobStatus.CREATED);

            if (!jobs.isEmpty()) {
                startJob(jobs.get(0));
            }

            TimeUnit.SECONDS.sleep(startInterval);
        }
    }

    public synchronized void stop() {
        this.isRunning = false;
    }

    public synchronized boolean isRunning() {
        return this.isRunning;
    }

    private void startJob(BenchmarkJob job) throws IOException, InterruptedException {
        if (job.getClass() == DockerBenchmarkJob.class) {
            log.info("starting docker benchmark job: {}", job);
            this.dockerExecutionService.executeBenchmarkJob(job);
        } else if (job.getClass() == GitBenchmarkJob.class) {
            log.info("starting git benchmark job: {}", job);
            this.gitJobExecutionService.executeBenchmarkJob(job);
        }
    }

}
