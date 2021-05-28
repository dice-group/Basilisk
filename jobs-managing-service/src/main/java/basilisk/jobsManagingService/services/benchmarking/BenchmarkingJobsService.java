package basilisk.jobsManagingService.services.benchmarking;


import basilisk.jobsManagingService.events.DockerImageCreatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;

/**
 * @author Fakhr Shaheen
 */

public interface BenchmarkingJobsService {

    /**
     * generates a list of benchmarking jobs based on git commit, one for each dataset
     *
     * @param gitCommitAddedEvent
     */
    void generateBenchmarkingJobs(GitCommitAddedEvent gitCommitAddedEvent);

    /**
     * generates a list of benchmarking jobs based on docker image created event, one for each dataset
     *
     * @param dockerImageCreatedEvent
     */
    void generateBenchmarkingJobs(DockerImageCreatedEvent dockerImageCreatedEvent);

    void setJobStatusAsStarted(long jobId);

    void setJobStatusAsFinished(long jobId);

    void setJobStatusAsAborted(long jobId);

    void setJobStatusAsFailed(long jobId);

    void abortJob(long jobId);


}
