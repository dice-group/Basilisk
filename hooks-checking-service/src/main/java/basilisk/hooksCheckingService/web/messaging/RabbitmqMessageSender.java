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

    @Value("${hooksCheckingService.rabbitmq.dockerRoutingkeys.Repo}")
    private String dockerRepoRoutingkey;

    @Value("${hooksCheckingService.rabbitmq.dockerRoutingkeys.Image}")
    private String dockerImageRoutingkey;

    @Value("${hooksCheckingService.rabbitmq.dockerRoutingkeys.Tag}")
    private String dockerTagRoutingkey;


    @Value("${hooksCheckingService.rabbitmq.gitRoutingkeys.Repo}")
    private String gitRepoRoutingkey;

    @Value("${hooksCheckingService.rabbitmq.gitRoutingkeys.Commit}")
    private String gitCommitRoutingkey;


    @Override
    public void send(GitRepoAddedEvent addedRepo) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, gitRepoRoutingkey, addedRepo);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(GitCommitAddedEvent addedHook) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, gitCommitRoutingkey, addedHook);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerRepoAddedEvent addedRepo) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerRepoRoutingkey, addedRepo);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerImageCreatedEvent createdImage) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerImageRoutingkey, createdImage);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerTagAddedEvent addedTag) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerTagRoutingkey, addedTag);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }

    @Override
    public void send(DockerTagUpdatedEvent updatedTag) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, dockerTagRoutingkey, updatedTag);
        }
        catch (Exception e)
        {
            throw new MessageSendingExecption();
        }
    }
}