package basilisk.hooksCheckingService.web.messaging;

/**
 * @author Fakhr Shaheen
 */

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.events.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqMessageSender implements MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${hooksCheckingService.rabbitmq.exchange}")
    private String exchange;

    @Value("${hooksCheckingService.rabbitmq.dockerRoutingkey}")
    private String dockerRoutingkey;

    @Value("${hooksCheckingService.rabbitmq.gitRoutingkey}")
    private String gitRoutingkey;


    @Override
    public void send(GitRepoAddedEvent addedRepo) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, gitRoutingkey, addedRepo);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(GitCommitAddedEvent addedHook) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, gitRoutingkey, addedHook);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerRepoAddedEvent addedRepo) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerRoutingkey, addedRepo);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerImageCreatedEvent createdImage) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerRoutingkey, createdImage);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerTagAddedEvent addedTag) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerRoutingkey, addedTag);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerTagUpdatedEvent updatedTag) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerRoutingkey, updatedTag);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }
}