package org.dicegroup.basilisk.jobsManagingService.services.benchmarking;

import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.dto.benchmark.BenchmarkDto;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;
import org.dicegroup.basilisk.dto.repo.GitRepoDto;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortCommand;
import org.dicegroup.basilisk.events.benchmark.DockerBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.benchmark.GitBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.hooks.hook.DockerTagEvent;
import org.dicegroup.basilisk.events.hooks.hook.GitCommitEvent;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.*;
import org.dicegroup.basilisk.jobsManagingService.model.repo.DockerRepo;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.BenchmarkJobRepository;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.DockerBenchmarkJobRepository;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.GitBenchmarkJobRepository;
import org.dicegroup.basilisk.jobsManagingService.services.repo.DockerRepoService;
import org.dicegroup.basilisk.jobsManagingService.web.messaging.benchmarking.BenchmarkMessageSender;
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
    private final TripleStoreService tripleStoreService;
    private final BenchmarkMessageSender messageSender;

    private final ModelMapper mapper;

    public BenchmarkJobService(DockerRepoService repoService, BenchmarkJobRepository benchmarkJobRepository, TripleStoreService tripleStoreService, BenchmarkMessageSender messageSender, DockerBenchmarkJobRepository dockerBenchmarkJobRepository, GitBenchmarkJobRepository gitBenchmarkJobRepository, BenchmarkService benchmarkService, DataSetService dataSetService, ModelMapper mapper) {
        this.repoService = repoService;
        this.benchmarkJobRepository = benchmarkJobRepository;
        this.tripleStoreService = tripleStoreService;
        this.dockerBenchmarkJobRepository = dockerBenchmarkJobRepository;
        this.messageSender = messageSender;
        this.gitBenchmarkJobRepository = gitBenchmarkJobRepository;
        this.benchmarkService = benchmarkService;
        this.dataSetService = dataSetService;
        this.mapper = mapper;
    }

    public List<BenchmarkJob> getAllBenchmarkJobs() {
        return (List<BenchmarkJob>) this.benchmarkJobRepository.findAll();
    }

    public List<DockerBenchmarkJob> getAllDockerBenchmarkJobs() {
        return (List<DockerBenchmarkJob>) this.dockerBenchmarkJobRepository.findAll();
    }

    public List<GitBenchmarkJob> getAllGitBenchmarkJobs() {
        return (List<GitBenchmarkJob>) this.gitBenchmarkJobRepository.findAll();
    }

    public void generateBenchmarkingJobs(GitCommitEvent event) {

        List<GitBenchmarkJob> jobs = generateGitBenchmarkingJobsConfigs(event);
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
        for (DockerBenchmarkJob job : generateDockerBenchmarkJobs(event)) {

            DockerBenchmarkJobCreateEvent jobEvent = DockerBenchmarkJobCreateEvent.builder()
                    .repo(this.mapper.map(job.getRepo(), DockerRepoDto.class))
                    .tagName(event.getTagName())
                    .imageDigest(event.getImageDigest())
                    .benchmark(this.mapper.map(job.getBenchmark(), BenchmarkDto.class))
                    .build();

            this.messageSender.send(jobEvent);
        }
    }

    private List<DockerBenchmarkJob> generateDockerBenchmarkJobs(DockerTagEvent event) {
        List<DockerBenchmarkJob> jobs = new ArrayList<>();

        DockerRepo repo = this.repoService.getRepo(event.getRepoId()).get();

        for (Benchmark benchmark : this.benchmarkService.getAllBenchmarks()) {
            DockerBenchmarkJob job = DockerBenchmarkJob.builder()
                    .repo(repo)
                    .tagName(event.getTagName())
                    .benchmark(benchmark)
                    .status(JobStatus.CREATED)
                    .build();

            jobs.add(job);
            this.dockerBenchmarkJobRepository.save(job);
        }

        return jobs;
    }

    public void setJobStatusAsStarted(long jobId) {
        Optional<BenchmarkJob> job = this.benchmarkJobRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.STARTED);
            this.benchmarkJobRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }

    }

    public void setJobStatusAsFinished(long jobId) {
        Optional<BenchmarkJob> job = benchmarkJobRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.FINISHED);
            this.benchmarkJobRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }
    }

    public void setJobStatusAsAborted(long jobId) {
        Optional<BenchmarkJob> job = this.benchmarkJobRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.ABORTED);
            this.benchmarkJobRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }
    }

    public void setJobStatusAsFailed(long jobId) {
        Optional<BenchmarkJob> job = this.benchmarkJobRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.FAILED);
            this.benchmarkJobRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }
    }

    public void abortJob(long jobId) {
        Optional<BenchmarkJob> job = this.benchmarkJobRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.ABORTING);
            this.benchmarkJobRepository.save(benchmarkJob);
            BenchmarkJobAbortCommand benchmarkJobAbortCommand = new BenchmarkJobAbortCommand(jobId);
            this.messageSender.send(benchmarkJobAbortCommand);
        } else {
            //ToDo log
        }
    }


    private List<GitBenchmarkJob> generateGitBenchmarkingJobsConfigs(GitCommitEvent gitCommitAddedEvent) {
        List<GitBenchmarkJob> jobs = new ArrayList<>();
        //get all active query and dataset configs
        List<DataSet> dataSets = this.dataSetService.getAllDataSets();

        // check the corresponding triple store
        //Optional<TripleStore> tripleStore= tripleStoreService.getTripleStoreByGitRepoId(gitCommitAddedEvent.getRepoId());
        //if(tripleStore.isEmpty())
        //{
        //ToDo
        //}
        dataSets.forEach(dataset -> {
            GitBenchmarkJob benchmarkJob = GitBenchmarkJob.builder()
                    //.tripleStore(tripleStore.get()) // TODO!
                    .status(JobStatus.CREATED)
                    .build();
            jobs.add(benchmarkJob);
        });

        return jobs;
    }


}
