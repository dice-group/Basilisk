package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.DockerJobConfig;
import basilisk.jobsManagingService.domain.GitJobConfig;
import basilisk.jobsManagingService.domain.TripleStore;
import basilisk.jobsManagingService.domain.benchmarking.*;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobAbortCommand;
import basilisk.jobsManagingService.events.BenchmarkJob.BenchmarkJobCreatedEvent;
import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.repositories.benchmarking.JobsRepository;
import basilisk.jobsManagingService.services.TripleStoreService;
import basilisk.jobsManagingService.web.messaging.MessageSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */

@Service
public class BenchmarkingJobsServiceImpl implements BenchmarkingJobsService{

    private JobsRepository jobsRepository;
    private BenchmarkConfigurationService benchmarkConfigurationService;
    private TripleStoreService tripleStoreService;
    private MessageSender messageSender;

    public BenchmarkingJobsServiceImpl(BenchmarkConfigurationService benchmarkConfigurationService,TripleStoreService tripleStoreService
            , MessageSender messageSender,JobsRepository jobsRepository) {
        this.benchmarkConfigurationService = benchmarkConfigurationService;
        this.tripleStoreService=tripleStoreService;
        this.jobsRepository=jobsRepository;
        this.messageSender = messageSender;
    }


    @Override
    public void generateBenchmarkingJobs(GitCommitAddedEvent gitCommitAddedEvent) {

        List<GitBenchmarkJob> jobs=generateGitBenchmarkingJobsConfigs(gitCommitAddedEvent);
        jobs.stream().forEach(job->
        {
            // store created jon in database
            jobsRepository.save(job);
            // send created job to message queue
            BenchmarkJobCreatedEvent benchmarkJobCreatedEvent=BenchmarkJobCreatedEvent.builder()
                    .benchmarkJob(job)
                    .createdDate(LocalDate.now())
                    .build();
            messageSender.send(benchmarkJobCreatedEvent);
        });
    }

    @Override
    public void generateBenchmarkingJobs(DockerImageCreatedEvent dockerImageCreatedEvent) {

        List<DockerBenchmarkJob> jobs=generateDockerBenchmarkingJobsConfigs(dockerImageCreatedEvent);
        jobs.stream().forEach(job->
        {
            // store the created job in the database
            jobsRepository.save(job);

            // send created job to the message queue
            BenchmarkJobCreatedEvent benchmarkJobCreatedEvent=BenchmarkJobCreatedEvent.builder()
                    .benchmarkJob(job)
                    .createdDate(LocalDate.now())
                    .build();
            messageSender.send(benchmarkJobCreatedEvent);
        });
    }

    @Override
    public void setJobStatusAsStarted(long jobId) {
        Optional<BenchmarkJob> job= jobsRepository.findById(jobId);
        if(job.isPresent())
        {
            BenchmarkJob benchmarkJob=job.get();
            benchmarkJob.setStatus(JobStatus.STARTED);
            jobsRepository.save(benchmarkJob);

        }
        else
        {
            //ToDo log
        }

    }

    @Override
    public void setJobStatusAsFinished(long jobId) {
        Optional<BenchmarkJob> job= jobsRepository.findById(jobId);
        if(job.isPresent())
        {
            BenchmarkJob benchmarkJob=job.get();
            benchmarkJob.setStatus(JobStatus.FINISHED);
            jobsRepository.save(benchmarkJob);

        }
        else
        {
            //ToDo log
        }
    }

    @Override
    public void setJobStatusAsAborted(long jobId) {
        Optional<BenchmarkJob> job= jobsRepository.findById(jobId);
        if(job.isPresent())
        {
            BenchmarkJob benchmarkJob=job.get();
            benchmarkJob.setStatus(JobStatus.ABORTED);
            jobsRepository.save(benchmarkJob);

        }
        else
        {
            //ToDo log
        }
    }

    @Override
    public void setJobStatusAsFailed(long jobId) {
        Optional<BenchmarkJob> job= jobsRepository.findById(jobId);
        if(job.isPresent())
        {
            BenchmarkJob benchmarkJob=job.get();
            benchmarkJob.setStatus(JobStatus.FAILED);
            jobsRepository.save(benchmarkJob);

        }
        else
        {
            //ToDo log
        }
    }

    @Override
    public void abortJob(long jobId)
    {
        Optional<BenchmarkJob> job= jobsRepository.findById(jobId);
        if(job.isPresent())
        {
            BenchmarkJob benchmarkJob=job.get();
            benchmarkJob.setStatus(JobStatus.ABORTING);
            jobsRepository.save(benchmarkJob);
            BenchmarkJobAbortCommand benchmarkJobAbortCommand=new BenchmarkJobAbortCommand(jobId);
            messageSender.send(benchmarkJobAbortCommand);
        }
        else
        {
            //ToDo log
        }
    }

    /**
     * Create a git becnhmarking job for each dataset
     * @param gitCommitAddedEvent
     * @return Created jobs
     */
    private List<GitBenchmarkJob> generateGitBenchmarkingJobsConfigs(GitCommitAddedEvent gitCommitAddedEvent)
    {
        List<GitBenchmarkJob> jobs=new ArrayList<>();
        //get all active query and dataset configs
        List<QueryConfig> activeQueryConfigs=benchmarkConfigurationService.getAllActiveBenchmarkQueryConfigs();
        List<DataSetConfig> dataSetConfigs=benchmarkConfigurationService.getAllActiveBenchmarkDataSetConfigs();
        GitJobConfig gitJobConfig=GitJobConfig.builder()
                .commit_sha1(gitCommitAddedEvent.getCommit_sha1())
                .url(gitCommitAddedEvent.getUrl())
                .commitCreationDate(gitCommitAddedEvent.getCommitCreationDate())
                .build();

        // check the corresponding triple store
        Optional<TripleStore> tripleStore= tripleStoreService.getTripleStoreByGitRepoId(gitCommitAddedEvent.getRepoId());
        if(tripleStore.isEmpty())
        {
            //ToDo
        }
        dataSetConfigs.stream().forEach(dataset ->
        {
            GitBenchmarkJob benchmarkJob=GitBenchmarkJob.builder()
                    .gitJobConfig(gitJobConfig)
                    .queryConfigs(activeQueryConfigs)
                    .dataSetConfig(dataset)
                    .tripleStore(tripleStore.get())
                    .status(JobStatus.CREATED)
                    .build();
            jobs.add(benchmarkJob);
        });

        return jobs;
    }

    /**
     * Create a docker becnhmarking job for each dataset
     * @param dockerImageCreatedEvent
     * @return Created jobs
     */
    private List<DockerBenchmarkJob> generateDockerBenchmarkingJobsConfigs(DockerImageCreatedEvent dockerImageCreatedEvent)
    {
        List<DockerBenchmarkJob> jobs=new ArrayList<>();
        //get all active query and dataset configs
        List<QueryConfig> activeQueryConfigs=benchmarkConfigurationService.getAllActiveBenchmarkQueryConfigs();
        List<DataSetConfig> dataSetConfigs=benchmarkConfigurationService.getAllActiveBenchmarkDataSetConfigs();
        DockerJobConfig dockerJobConfig=DockerJobConfig.builder()
                .imageId(dockerImageCreatedEvent.getImageId())
                .digest(dockerImageCreatedEvent.getDigest())
                .imageCreationDate(dockerImageCreatedEvent.getImageCreationDate())
                .build();

        // check the corresponding triple store
        Optional<TripleStore> tripleStore= tripleStoreService.getTripleStoreByGitRepoId(dockerImageCreatedEvent.getRepoId());
        if(tripleStore.isEmpty())
        {
            //ToDo
        }
        dataSetConfigs.stream().forEach(dataset ->
        {
            DockerBenchmarkJob benchmarkJob=DockerBenchmarkJob.builder()
                    .dockerJobConfig(dockerJobConfig)
                    .queryConfigs(activeQueryConfigs)
                    .dataSetConfig(dataset)
                    .tripleStore(tripleStore.get())
                    .status(JobStatus.CREATED)
                    .build();
            jobs.add(benchmarkJob);
        });

        return jobs;
    }


}
