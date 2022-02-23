package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.hooks.DockerRepoAddedEvent;
import basilisk.jobsManagingService.events.hooks.DockerRepoDeletedEvent;
import basilisk.jobsManagingService.events.hooks.GitRepoAddedEvent;
import basilisk.jobsManagingService.events.hooks.GitRepoDeletedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HooksMessageSender {

    private final RabbitTemplate rabbitTemplate;

    public HooksMessageSender(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

    @Value("${rabbitmq.hooks.exchange}")
    private String exchange;

    @Value("${rabbitmq.hooks.docker.routingKeys.repo}")
    private String dockerRepoRoutingKey;
    @Value("${rabbitmq.hooks.git.routingKeys.repo}")
    private String gitRepoRoutingKey;

    public void send(GitRepoAddedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.gitRepoRoutingKey, event);
    }

    public void send(GitRepoDeletedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.gitRepoRoutingKey, event);
    }

    public void send(DockerRepoAddedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerRepoRoutingKey, event);
    }

    public void send(DockerRepoDeletedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerRepoRoutingKey, event);
    }
}
