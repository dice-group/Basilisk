package basilisk.hooksCheckingService.messaging;

/**
 * @author Fakhr Shaheen
 */

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.git.GitHook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqMessagingHandler implements MessagingHandler {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${hooksCheckingService.rabbitmq.exchange}")
    private String exchange;

    @Value("${hooksCheckingService.rabbitmq.dockerRoutingkey}")
    private String dockerRoutingkey;

    @Value("${hooksCheckingService.rabbitmq.gitRoutingkey}")
    private String gitRoutingkey;

    public void send(DockerImage dockerImage) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerRoutingkey, dockerImage);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }

    }

    @Override
    public void send(GitHook gitHook)  throws MessageSendingExecption{
        try {
            rabbitTemplate.convertAndSend(exchange, gitRoutingkey, gitHook);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }

    }
}