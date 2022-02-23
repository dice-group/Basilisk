package basilisk.hooksCheckingService.web.messaging;

import basilisk.hooksCheckingService.events.docker.DockerTagAddedEvent;
import basilisk.hooksCheckingService.events.docker.DockerTagUpdatedEvent;
import basilisk.hooksCheckingService.events.git.GitCommitAddedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.hooks.exchange}")
    private String exchange;
    @Value("${rabbitmq.hooks.docker.routingKeys.tag}")
    private String dockerTagRoutingKey;
    @Value("${rabbitmq.hooks.git.routingKeys.commit}")
    private String gitCommitRoutingKey;

    public MessageSender(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

    public void send(GitCommitAddedEvent addedHook) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.gitCommitRoutingKey, addedHook);
    }

    public void send(DockerTagAddedEvent addedTag) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerTagRoutingKey, addedTag);
    }

    public void send(DockerTagUpdatedEvent updatedTag) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerTagRoutingKey, updatedTag);
    }
}