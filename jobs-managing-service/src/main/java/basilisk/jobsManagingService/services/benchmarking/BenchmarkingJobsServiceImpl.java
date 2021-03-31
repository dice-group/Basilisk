package basilisk.jobsManagingService.services.benchmarking;


import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class BenchmarkingJobsServiceImpl implements BenchmarkingJobsService{


    public BenchmarkingJobsServiceImpl( ) {
    }

    @Override
    public void createGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent) {

    }

    @Override
    public void createDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent) {

    }
}
