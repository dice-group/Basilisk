package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.hooks.DockerRepoAddedEvent;
import basilisk.jobsManagingService.events.hooks.DockerRepoDeletedEvent;
import basilisk.jobsManagingService.events.hooks.GitRepoAddedEvent;
import basilisk.jobsManagingService.events.hooks.GitRepoDeletedEvent;

public interface HooksMessageSender {

    void send(GitRepoAddedEvent event);
    void send(GitRepoDeletedEvent event);

    void send(DockerRepoAddedEvent event);
    void send(DockerRepoDeletedEvent event);
}
