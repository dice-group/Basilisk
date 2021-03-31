package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.BenchmarkJob;
import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;

/**
 * @author Fakhr Shaheen
 */

public interface BenchmarkingJobsService {

    public BenchmarkJob createGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent);
    public BenchmarkJob createDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent);
}
