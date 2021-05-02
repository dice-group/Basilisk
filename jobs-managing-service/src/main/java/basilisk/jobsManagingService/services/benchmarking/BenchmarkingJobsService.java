package basilisk.jobsManagingService.services.benchmarking;


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
     * generates a list of benchmarking jobs based on git commit, one for each dataset
     * @param gitCommitAddedEvent
     * @return a list of generated jobs
     */
    public List<GitBenchmarkJob> generateGitBenchmarkingJob(GitCommitAddedEvent gitCommitAddedEvent);

    /**
     * generates a list of benchmarking jobs based on docker image created event, one for each dataset
     * @param dockerImageCreatedEvent
     * @return a list of generated jobs
     */
    public List<DockerBenchmarkJob> generateDockerBenchmarkingJob(DockerImageCreatedEvent dockerImageCreatedEvent);
}
