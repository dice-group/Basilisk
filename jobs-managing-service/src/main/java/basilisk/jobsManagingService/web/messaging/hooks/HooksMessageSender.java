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

    @Value("${rabbitmq.hooks.docker.routingKeys.repoAdded}")
    private String dockerRepoAddedRoutingKey;
    @Value("${rabbitmq.hooks.docker.routingKeys.repoDeleted}")
    private String dockerRepoDeletedRoutingKey;

    @Value("${rabbitmq.hooks.git.routingKeys.repoAdded}")
    private String gitRepoAddedRoutingKey;
    @Value("${rabbitmq.hooks.git.routingKeys.repoDeleted}")
    private String gitRepoDeletedRoutingKey;

    public void send(GitRepoAddedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.gitRepoAddedRoutingKey, event);
    }

    public void send(GitRepoDeletedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.gitRepoDeletedRoutingKey, event);
    }

    public void send(DockerRepoAddedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerRepoAddedRoutingKey, event);
    }

    public void send(DockerRepoDeletedEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerRepoDeletedRoutingKey, event);
    }
}
