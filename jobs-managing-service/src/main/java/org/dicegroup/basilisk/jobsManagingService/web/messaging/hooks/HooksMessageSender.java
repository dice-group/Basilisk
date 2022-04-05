package org.dicegroup.basilisk.jobsManagingService.web.messaging.hooks;

import org.dicegroup.basilisk.events.hooks.repo.DockerRepoAddEvent;
import org.dicegroup.basilisk.events.hooks.repo.DockerRepoDeleteEvent;
import org.dicegroup.basilisk.events.hooks.repo.GitRepoAddEvent;
import org.dicegroup.basilisk.events.hooks.repo.GitRepoDeleteEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HooksMessageSender {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.hooks.exchange}")
    private String exchange;
    @Value("${rabbitmq.hooks.repoRoutingKey}")
    private String repoRoutingKey;

    public HooksMessageSender(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }


    public void send(GitRepoAddEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.repoRoutingKey, event);
    }

    public void send(GitRepoDeleteEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.repoRoutingKey, event);
    }

    public void send(DockerRepoAddEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.repoRoutingKey, event);
    }

    public void send(DockerRepoDeleteEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.repoRoutingKey, event);
    }

}
