package basilisk.hooksCheckingService.web.messaging;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.events.*;

/**
 * @author Fakhr Shaheen
 */

public class MessageSenderDumpImpl implements MessageSender {


    @Override
    public void send(GitRepoAddedEvent addedRepo) throws MessageSendingExecption {

    }

    @Override
    public void send(GitCommitAddedEvent addedHook) throws MessageSendingExecption {

    }

    @Override
    public void send(DockerRepoAddedEvent addedRepo) throws MessageSendingExecption {

    }

    @Override
    public void send(DockerImageCreatedEvent createdImage) throws MessageSendingExecption {

    }

    @Override
    public void send(DockerTagAddedEvent addedTag) throws MessageSendingExecption {

    }

    @Override
    public void send(DockerTagUpdatedEvent updatedTag) throws MessageSendingExecption {

    }
}
