package basilisk.jobsManagingService.services.benchmarking;


import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;

/**
 * @author Fakhr Shaheen
 */

public interface BenchmarkingJobsService {

    /**
     * generates a list of benchmarking jobs based on git commit, one for each dataset
     * @param gitCommitAddedEvent
     */
    public void generateBenchmarkingJobs(GitCommitAddedEvent gitCommitAddedEvent);

    /**
     * generates a list of benchmarking jobs based on docker image created event, one for each dataset
     * @param dockerImageCreatedEvent
     */
    public void generateBenchmarkingJobs(DockerImageCreatedEvent dockerImageCreatedEvent);
}
