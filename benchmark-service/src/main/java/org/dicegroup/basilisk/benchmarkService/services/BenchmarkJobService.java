package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortedEvent;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.web.messaging.MessageSender;
import org.dicegroup.basilisk.dto.benchmark.JobStatus;
import org.dicegroup.basilisk.benchmarkService.repositories.BenchmarkJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BenchmarkJobService {

    private final BenchmarkJobRepository jobRepo;
    private final MessageSender messageSender;

    public BenchmarkJobService(BenchmarkJobRepository jobRepo, MessageSender messageSender) {
        this.jobRepo = jobRepo;
        this.messageSender = messageSender;
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

            this.messageSender.send(new BenchmarkJobAbortedEvent(jobId));
        }
    }

    public List<BenchmarkJob> getJobList() {
        return (List<BenchmarkJob>) this.jobRepo.findAll();
    }

}
