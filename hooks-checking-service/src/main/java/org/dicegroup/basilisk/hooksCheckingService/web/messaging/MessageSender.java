package org.dicegroup.basilisk.hooksCheckingService.web.messaging;

import org.dicegroup.basilisk.events.hooks.hook.DockerTagEvent;
import org.dicegroup.basilisk.events.hooks.hook.GitCommitEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.hooks.exchange}")
    private String exchange;
    @Value("${rabbitmq.hooks.hookEventRoutingKey}")
    private String hookEventRoutingKey;

    public MessageSender(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

    public void send(GitCommitEvent addedHook) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.hookEventRoutingKey, addedHook);
    }

    public void send(DockerTagEvent tagEvent) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.hookEventRoutingKey, tagEvent);
    }
}