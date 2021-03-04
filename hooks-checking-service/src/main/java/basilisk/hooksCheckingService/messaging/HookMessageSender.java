package basilisk.hooksCheckingService.messaging;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;

public interface HookMessageSender {

    public void send(DockerImage dockerImage) throws MessageSendingExecption;
}
