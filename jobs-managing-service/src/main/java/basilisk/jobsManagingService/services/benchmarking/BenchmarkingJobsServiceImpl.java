package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.GitJobConfig;
import basilisk.jobsManagingService.domain.TripleStore;
import basilisk.jobsManagingService.domain.benchmarking.*;
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

        // ToDo save in database
        // ToDo create and event and send it to the queue
        // ToDo test it
        jobs.stream().forEach(job->
        {
            jobsRepository.save(job);
            BenchmarkJobCreatedEvent benchmarkJobCreatedEvent=BenchmarkJobCreatedEvent.builder()
                    .benchmarkJob(job)
                    .createdDate(LocalDate.now())
                    .build();
            messageSender.send(benchmarkJobCreatedEvent);
        });
    }

    @Override
    public void generateBenchmarkingJobs(DockerImageCreatedEvent dockerImageCreatedEvent) {
        //ToDo
    }

}
