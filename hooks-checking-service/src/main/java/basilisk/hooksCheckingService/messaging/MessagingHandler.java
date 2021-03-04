package basilisk.hooksCheckingService.messaging;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.git.GitHook;

public interface MessagingHandler {

    public void send(DockerImage dockerImage) throws MessageSendingExecption;
    public void send(GitHook gitHook) throws MessageSendingExecption;
}
