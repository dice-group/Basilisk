package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;

/**
 * @author Fakhr Shaheen
 */

public interface BenchmarkingJobsService {

    public void createGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent);
    public void createDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent);
}
