package basilisk.jobsManagingService.web.messaging;

import basilisk.jobsManagingService.events.*;

/**
 * @author Fakhr Shaheen
 */
public interface MessageReceiver {

    public void receiveDockerImageEvent(DockerImageCreatedEvent dockerImageCreatedEvent);
    public void receiveDockerRepoEvent(DockerRepoAddedEvent dockerRepoAddedEvent);
    public void receiveDockerTagEvent(DockerTagUpdatedEvent dockerTagUpdatedEvent);
    public void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent);
    public void receiveGitRepoEvent(GitRepoAddedEvent gitRepoAddedEvent);
}
