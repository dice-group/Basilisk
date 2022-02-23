package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.DockerTagUpdatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;

public interface HooksMessageReceiver {

    void receiveDockerTagEvent(DockerTagUpdatedEvent dockerTagUpdatedEvent);
    void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent);

}
