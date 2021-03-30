package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.IguanaTask;
import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Component
public class BenchmarkingJobsServiceImpl implements BenchmarkingJobsService{

    IguanaConfigurationService iguanaConfigurationService;

    public BenchmarkingJobsServiceImpl(IguanaConfigurationService iguanaConfigurationService) {
        this.iguanaConfigurationService = iguanaConfigurationService;
    }

    @Override
    public void createGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent) {

    }

    @Override
    public void createDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent) {

    }
}
