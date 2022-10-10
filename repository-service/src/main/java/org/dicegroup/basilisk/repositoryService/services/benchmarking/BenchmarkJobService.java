package org.dicegroup.basilisk.repositoryService.services.benchmarking;

import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.dto.benchmark.BenchmarkDto;
import org.dicegroup.basilisk.dto.benchmark.JobStatus;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;
import org.dicegroup.basilisk.dto.repo.GitRepoDto;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortCommand;
import org.dicegroup.basilisk.events.benchmark.DockerBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.benchmark.GitBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.hook.DockerTagEvent;
import org.dicegroup.basilisk.events.hook.GitCommitEvent;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.*;
import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerRepo;
import org.dicegroup.basilisk.repositoryService.repositories.benchmarking.BenchmarkJobRepository;
import org.dicegroup.basilisk.repositoryService.repositories.benchmarking.DockerBenchmarkJobRepository;
import org.dicegroup.basilisk.repositoryService.repositories.benchmarking.GitBenchmarkJobRepository;
import org.dicegroup.basilisk.repositoryService.services.repo.DockerRepoService;
import org.dicegroup.basilisk.repositoryService.web.messaging.BenchmarkMessageSender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class BenchmarkJobService {

    private final DockerRepoService repoService;

    private final BenchmarkJobRepository benchmarkJobRepository;
    private final DockerBenchmarkJobRepository dockerBenchmarkJobRepository;
    private final GitBenchmarkJobRepository gitBenchmarkJobRepository;
    private final BenchmarkService benchmarkService;
    private final DataSetService dataSetService;
    private final BenchmarkMessageSender messageSender;

    private final ModelMapper mapper;

    public BenchmarkJobService(DockerRepoService repoService, BenchmarkJobRepository benchmarkJobRepository, BenchmarkMessageSender messageSender, DockerBenchmarkJobRepository dockerBenchmarkJobRepository, GitBenchmarkJobRepository gitBenchmarkJobRepository, BenchmarkService benchmarkService, DataSetService dataSetService, ModelMapper mapper) {
        this.repoService = repoService;
        this.benchmarkJobRepository = benchmarkJobRepository;
        this.dockerBenchmarkJobRepository = dockerBenchmarkJobRepository;
        this.messageSender = messageSender;
        this.gitBenchmarkJobRepository = gitBenchmarkJobRepository;
        this.benchmarkService = benchmarkService;
        this.dataSetService = dataSetService;
        this.mapper = mapper;
    }

    public Optional<BenchmarkJob> getBenchmarkJob(Long id) {
        return this.benchmarkJobRepository.findById(id);
    }

    public List<BenchmarkJob> getAllBenchmarkJobs() {
        return (List<BenchmarkJob>) this.benchmarkJobRepository.findAll();
    }

    public List<BenchmarkJob> getAllPendingJobs() {
        return this.benchmarkJobRepository.findAllByStatus(JobStatus.CREATED);
    }

    public List<DockerBenchmarkJob> getAllDockerBenchmarkJobs() {
        return (List<DockerBenchmarkJob>) this.dockerBenchmarkJobRepository.findAll();
    }

    public List<GitBenchmarkJob> getAllGitBenchmarkJobs() {
        return (List<GitBenchmarkJob>) this.gitBenchmarkJobRepository.findAll();
    }

    public String createManualBenchmarkJob(Long repoId, String tagName, Long benchmarkId) {
        Optional<DockerRepo> repoOpt = this.repoService.getRepo(repoId);
        if (repoOpt.isEmpty()) {
            log.info("Repo with id {} not found", repoId);
            return null;
        }

        Optional<Benchmark> benchmarkOpt = this.benchmarkService.getBenchmark(benchmarkId);
        if (benchmarkOpt.isEmpty()) {
            log.info("Benchmark with id {} not found", benchmarkId);
            return null;
        }

        DockerBenchmarkJob job = DockerBenchmarkJob.builder()
                .repo(repoOpt.get())
                .benchmark(benchmarkOpt.get())
                .tagName(tagName)
                .status(JobStatus.CREATED)
                .build();

        sendBenchmarkJob(this.dockerBenchmarkJobRepository.save(job));

        return "created manual benchmark job";
    }

    public void generateBenchmarkingJobs(GitCommitEvent event) {

        List<GitBenchmarkJob> jobs = generateGitBenchmarkJobsConfigs(event);
        jobs.forEach(job -> {
            this.gitBenchmarkJobRepository.save(job);
            // send created job to message queue
            GitBenchmarkJobCreateEvent gitBenchmarkJobCreatedEvent = GitBenchmarkJobCreateEvent.builder()
                    .repo(this.mapper.map(job.getRepo(), GitRepoDto.class))
                    .url(job.getUrl())
                    .commitSha1(job.getCommitSha1())
                    .build();
            this.messageSender.send(gitBenchmarkJobCreatedEvent);
        });
    }

    public void generateBenchmarkJobs(DockerTagEvent event) {

        Optional<DockerRepo> repoOpt = this.repoService.getRepo(event.getRepoId());
        if (repoOpt.isEmpty()) {
            return;
        }

        for (Benchmark benchmark : this.benchmarkService.getAllBenchmarks()) {
            DockerBenchmarkJob job = DockerBenchmarkJob.builder()
                    .repo(repoOpt.get())
                    .tagName(event.getTagName())
                    .benchmark(benchmark)
                    .status(JobStatus.CREATED)
                    .imageDigest(event.getImageDigest())
                    .build();

            job = this.dockerBenchmarkJobRepository.save(job);
            sendBenchmarkJob(job);
        }
    }

    private void sendBenchmarkJob(DockerBenchmarkJob job) {
        DockerBenchmarkJobCreateEvent event = DockerBenchmarkJobCreateEvent.builder()
                .jobId(job.getId())
                .repo(this.mapper.map(job.getRepo(), DockerRepoDto.class))
                .tagName(job.getTagName())
                .benchmark(this.mapper.map(job.getBenchmark(), BenchmarkDto.class))
                .build();
        this.messageSender.send(event);
    }

    public void setJobStatusAsStarted(long jobId) {
        setJobStatus(jobId, JobStatus.STARTED);
    }

    public void setJobStatusAsFinished(long jobId) {
        setJobStatus(jobId, JobStatus.FINISHED);
    }

    public void setJobStatusAsAborted(long jobId) {
        setJobStatus(jobId, JobStatus.ABORTED);
    }

    public void setJobStatusAsFailed(long jobId) {
        setJobStatus(jobId, JobStatus.FAILED);
    }

    private void setJobStatus(long jobId, JobStatus status) {
        Optional<BenchmarkJob> jobOpt = this.benchmarkJobRepository.findById(jobId);
        if (jobOpt.isEmpty()) {
            return;
        }
        BenchmarkJob job = jobOpt.get();
        job.setStatus(status);
        this.benchmarkJobRepository.save(job);
    }

    public String abortJob(long jobId) {
        Optional<BenchmarkJob> job = this.benchmarkJobRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.ABORTING);
            this.benchmarkJobRepository.save(benchmarkJob);
            this.messageSender.send(new BenchmarkJobAbortCommand(jobId));
            return "Job aborting..";
        } else {
            log.info("Job with id {} not found!", jobId);
            return "Job with id " + jobId + " not found!";
        }
    }


    private List<GitBenchmarkJob> generateGitBenchmarkJobsConfigs(GitCommitEvent gitCommitAddedEvent) {
        List<GitBenchmarkJob> jobs = new ArrayList<>();
        //get all active query and dataset configs
        List<DataSet> dataSets = this.dataSetService.getAllDataSets();

        dataSets.forEach(dataset -> {
            GitBenchmarkJob benchmarkJob = GitBenchmarkJob.builder()
                    //.tripleStore(tripleStore.get()) // TODO!
                    .status(JobStatus.CREATED)
                    .build();
            jobs.add(benchmarkJob);
        });

        return jobs;
    }


    public void deleteBenchmarkJob(BenchmarkJob job) {
        this.benchmarkJobRepository.delete(job);
    }

}
