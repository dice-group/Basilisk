package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.JobStatus;
import org.dicegroup.basilisk.benchmarkService.repositories.BenchmarkJobRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BenchmarkJobService {

    private final BenchmarkJobRepository jobRepo;

    public BenchmarkJobService(BenchmarkJobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    public void addDockerBenchmarkJob(DockerBenchmarkJob job) {
        job.setStatus(JobStatus.CREATED);
        this.jobRepo.save(job);
    }

    public void abortJob(Long jobId) {
        Optional<BenchmarkJob> jobOpt = this.jobRepo.findById(jobId);

        BenchmarkJob job;
        if (jobOpt.isPresent()) {
            job = jobOpt.get();
            job.setStatus(JobStatus.ABORTED);

            this.jobRepo.save(job);
        }
    }

}
