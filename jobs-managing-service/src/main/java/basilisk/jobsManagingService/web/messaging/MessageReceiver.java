package basilisk.jobsManagingService.web.messaging;

import basilisk.jobsManagingService.events.*;
import basilisk.jobsManagingService.events.BenchmarkJob.*;

/**
 * @author Fakhr Shaheen
 */
public interface MessageReceiver {

    public void receiveDockerImageEvent(DockerImageCreatedEvent dockerImageCreatedEvent);
    public void receiveDockerRepoEvent(DockerRepoAddedEvent dockerRepoAddedEvent);
    public void receiveDockerTagEvent(DockerTagUpdatedEvent dockerTagUpdatedEvent);
    public void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent);
    public void receiveGitRepoEvent(GitRepoAddedEvent gitRepoAddedEvent);


    public void receive(BenchmarkJobStartedEvent benchmarkJobStartedEvent);
    public void receive(BenchmarkJobAbortedEvent benchmarkJobAbortedEvent);
    public void receive(BenchmarkJobFinishedEvent benchmarkJobFinishedEvent);
    public void receive(BenchmarkJobFailedEvent benchmarkJobFailedEvent);

    public void receive(BenchmarkJobAbortCommand benchmarkJobAbortCommand);
}
