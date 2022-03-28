package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.core.exception.MessageSendingExecption;
import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.events.benchmarking.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.benchmarking.BenchmarkJobCreatedEvent;
import basilisk.jobsManagingService.model.DockerJobConfig;
import basilisk.jobsManagingService.model.GitJobConfig;
import basilisk.jobsManagingService.model.benchmarking.*;
import basilisk.jobsManagingService.repositories.benchmarking.JobsRepository;
import basilisk.jobsManagingService.web.messaging.benchmarking.BenchmarkMessageSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BenchmarkingJobsService {

    private final JobsRepository jobsRepository;
    private final QueryConfigService qcService;
    private final DataSetConfigService dcService;
    private final TripleStoreService tripleStoreService;
    private final BenchmarkMessageSender messageSender;

    public BenchmarkingJobsService(QueryConfigService qcService, TripleStoreService tripleStoreService, BenchmarkMessageSender messageSender, JobsRepository jobsRepository, DataSetConfigService dcService) {
        this.qcService = qcService;
        this.tripleStoreService = tripleStoreService;
        this.jobsRepository = jobsRepository;
        this.messageSender = messageSender;
        this.dcService = dcService;
    }


    public void generateBenchmarkingJobs(GitCommitAddedEvent gitCommitAddedEvent) {

        List<GitBenchmarkJob> jobs = generateGitBenchmarkingJobsConfigs(gitCommitAddedEvent);
        jobs.forEach(job -> {
            this.jobsRepository.save(job);
            // send created job to message queue
            BenchmarkJobCreatedEvent benchmarkJobCreatedEvent = BenchmarkJobCreatedEvent.builder()
                    .benchmarkJob(job)
                    .createdDate(LocalDate.now())
                    .build();
            try {
                this.messageSender.send(benchmarkJobCreatedEvent);
            } catch (MessageSendingExecption e) {
                e.printStackTrace();
            }
        });
    }

    public void generateBenchmarkingJobs(DockerImageCreatedEvent dockerImageCreatedEvent) {

        List<DockerBenchmarkJob> jobs = generateDockerBenchmarkingJobsConfigs(dockerImageCreatedEvent);
        jobs.forEach(job ->
        {
            // store the created job in the database
            this.jobsRepository.save(job);

            // send created job to the message queue
            BenchmarkJobCreatedEvent benchmarkJobCreatedEvent = BenchmarkJobCreatedEvent.builder()
                    .benchmarkJob(job)
                    .createdDate(LocalDate.now())
                    .build();
            try {
                this.messageSender.send(benchmarkJobCreatedEvent);
            } catch (MessageSendingExecption e) {
                e.printStackTrace();
            }
        });
    }

    public void setJobStatusAsStarted(long jobId) {
        Optional<BenchmarkJob> job = this.jobsRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.STARTED);
            this.jobsRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }

    }

    public void setJobStatusAsFinished(long jobId) {
        Optional<BenchmarkJob> job = jobsRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.FINISHED);
            this.jobsRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }
    }

    public void setJobStatusAsAborted(long jobId) {
        Optional<BenchmarkJob> job = this.jobsRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.ABORTED);
            this.jobsRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }
    }

    public void setJobStatusAsFailed(long jobId) {
        Optional<BenchmarkJob> job = this.jobsRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.FAILED);
            this.jobsRepository.save(benchmarkJob);

        } else {
            //ToDo log
        }
    }

    public void abortJob(long jobId) {
        Optional<BenchmarkJob> job = this.jobsRepository.findById(jobId);
        if (job.isPresent()) {
            BenchmarkJob benchmarkJob = job.get();
            benchmarkJob.setStatus(JobStatus.ABORTING);
            this.jobsRepository.save(benchmarkJob);
            BenchmarkJobAbortCommand benchmarkJobAbortCommand = new BenchmarkJobAbortCommand(jobId);
            try {
                this.messageSender.send(benchmarkJobAbortCommand);
            } catch (MessageSendingExecption e) {
                e.printStackTrace();
            }
        } else {
            //ToDo log
        }
    }


    private List<GitBenchmarkJob> generateGitBenchmarkingJobsConfigs(GitCommitAddedEvent gitCommitAddedEvent) {
        List<GitBenchmarkJob> jobs = new ArrayList<>();
        //get all active query and dataset configs
        List<QueryConfig> activeQueryConfigs = this.qcService.getAllActiveQueryConfigs();
        List<DataSetConfig> dataSetConfigs = this.dcService.getAllActiveDataSetConfigs();
        GitJobConfig gitJobConfig = GitJobConfig.builder()
                .commit_sha1(gitCommitAddedEvent.getCommit_sha1())
                .url(gitCommitAddedEvent.getUrl())
                .build();

        // check the corresponding triple store
        //Optional<TripleStore> tripleStore= tripleStoreService.getTripleStoreByGitRepoId(gitCommitAddedEvent.getRepoId());
        //if(tripleStore.isEmpty())
        //{
        //ToDo
        //}
        dataSetConfigs.forEach(dataset -> {
            GitBenchmarkJob benchmarkJob = GitBenchmarkJob.builder()
                    .gitJobConfig(gitJobConfig)
                    .queryConfigs(activeQueryConfigs)
                    .dataSetConfig(dataset)
                    //.tripleStore(tripleStore.get()) // TODO!
                    .status(JobStatus.CREATED)
                    .build();
            jobs.add(benchmarkJob);
        });

        return jobs;
    }

    /**
     * Create a docker becnhmarking job for each dataset
     *
     * @param dockerImageCreatedEvent
     * @return Created jobs
     */
    private List<DockerBenchmarkJob> generateDockerBenchmarkingJobsConfigs(DockerImageCreatedEvent dockerImageCreatedEvent) {
        List<DockerBenchmarkJob> jobs = new ArrayList<>();
        //get all active query and dataset configs
        List<QueryConfig> activeQueryConfigs = this.qcService.getAllActiveQueryConfigs();
        List<DataSetConfig> dataSetConfigs = this.dcService.getAllActiveDataSetConfigs();
        DockerJobConfig dockerJobConfig = DockerJobConfig.builder()
                .imageId(dockerImageCreatedEvent.getImageId())
                .digest(dockerImageCreatedEvent.getDigest())
                .imageCreationDate(dockerImageCreatedEvent.getImageCreationDate())
                .build();

        // check the corresponding triple store
//        Optional<TripleStore> tripleStore= tripleStoreService.getTripleStoreByGitRepoId(dockerImageCreatedEvent.getRepoId());
//        if(tripleStore.isEmpty())
//        {
//            //ToDo
//        }
        dataSetConfigs.forEach(dataset ->
        {
            DockerBenchmarkJob benchmarkJob = DockerBenchmarkJob.builder()
                    .dockerJobConfig(dockerJobConfig)
                    .queryConfigs(activeQueryConfigs)
                    .dataSetConfig(dataset)
                    //.tripleStore(tripleStore.get()) // TODO!
                    .status(JobStatus.CREATED)
                    .build();
            jobs.add(benchmarkJob);
        });

        return jobs;
    }


}
