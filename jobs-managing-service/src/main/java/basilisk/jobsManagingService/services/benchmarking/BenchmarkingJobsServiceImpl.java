package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.DockerJobConfig;
import basilisk.jobsManagingService.domain.GitJobConfig;
import basilisk.jobsManagingService.domain.benchmarking.BenchmarkJob;
import basilisk.jobsManagingService.domain.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.domain.benchmarking.GitBenchmarkJob;
import basilisk.jobsManagingService.domain.benchmarking.QueryConfig;
import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.web.messaging.MessageSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Service
public class BenchmarkingJobsServiceImpl implements BenchmarkingJobsService{

    private BenchmarkConfigurationService benchmarkConfigurationService;
    private MessageSender messageSender;

    public BenchmarkingJobsServiceImpl(BenchmarkConfigurationService benchmarkConfigurationService, MessageSender messageSender) {
        this.benchmarkConfigurationService = benchmarkConfigurationService;
        this.messageSender = messageSender;
    }

    @Override
    public BenchmarkJob generateGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent) {

        //get all active query and dataset configs
        List<QueryConfig> activeQueryConfigs=benchmarkConfigurationService.getAllBenchmarkQueryConfigs();
        List<DataSetConfig> dataSetConfigs=benchmarkConfigurationService.getAllActiveBenchmarkDataSetConfigs();

        GitJobConfig gitJobConfig=GitJobConfig.builder()
                .commit_sha1(gitCommitAddedEvent.getCommit_sha1())
                .url(gitCommitAddedEvent.getUrl())
                .commitCreationDate(gitCommitAddedEvent.getCommitCreationDate())
                .build();

        GitBenchmarkJob benchmarkJob=GitBenchmarkJob.builder()
                .gitJobConfig(gitJobConfig)
                .queryConfigs(activeQueryConfigs)
                .dataSetConfigs(dataSetConfigs)
                .build();
        return benchmarkJob;
    }

    @Override
    public BenchmarkJob generateDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent) {

        return null;
    }

}
