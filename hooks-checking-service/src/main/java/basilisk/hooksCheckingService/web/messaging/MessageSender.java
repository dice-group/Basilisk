package basilisk.hooksCheckingService.web.messaging;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.events.*;

public interface MessageSender {

    public void send(GitRepoAddedEvent addedRepo)throws MessageSendingExecption;
    public void send(GitCommitAddedEvent addedHook)throws MessageSendingExecption;

    public void send(DockerRepoAddedEvent addedRepo)throws MessageSendingExecption;
    public void send(DockerImageCreatedEvent createdImage)throws MessageSendingExecption;
    public void send(DockerTagAddedEvent addedTag)throws MessageSendingExecption;
    public void send(DockerTagUpdatedEvent updatedTag)throws MessageSendingExecption;
}
