package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.BenchmarkJob;
import basilisk.jobsManagingService.domain.benchmarking.DockerBenchmarkJob;
import basilisk.jobsManagingService.domain.benchmarking.GitBenchmarkJob;
import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

public interface BenchmarkingJobsService {

    /**
     *
     * @param gitCommitAddedEvent
     * @return
     */
    public List<GitBenchmarkJob> generateGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent);
    public List<DockerBenchmarkJob> generateDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent);
}
