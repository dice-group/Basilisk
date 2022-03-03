package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.hooks.DockerRepoEvent;
import basilisk.jobsManagingService.events.hooks.GitRepoEvent;
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

    public void send(GitRepoEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.gitRepoRoutingKey, event);
    }

    public void send(DockerRepoEvent event) {
        this.rabbitTemplate.convertAndSend(this.exchange, this.dockerRepoRoutingKey, event);
    }
}
