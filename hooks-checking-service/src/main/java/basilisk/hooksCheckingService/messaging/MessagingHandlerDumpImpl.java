package basilisk.hooksCheckingService.messaging;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.git.GitHook;

/**
 * @author Fakhr Shaheen
 */

public class MessagingHandlerDumpImpl implements MessagingHandler {


    @Override
    public void send(DockerImage dockerImage) {

    }

    @Override
    public void send(GitHook gitHook) throws MessageSendingExecption {

    }
}
