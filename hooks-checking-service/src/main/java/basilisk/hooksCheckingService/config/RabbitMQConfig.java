package basilisk.hooksCheckingService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.hooks.exchange}")
    String hooksExchange;


    @Value("${rabbitmq.hooks.docker.repoAddedQueue}")
    String dockerRepoAddedQueue;
    @Value("${rabbitmq.hooks.docker.repoDeletedQueue}")
    String dockerRepoDeletedQueue;

    @Value("${rabbitmq.hooks.docker.tagQueue}")
    String dockerTagQueue;
    @Value("${rabbitmq.hooks.git.repoAddedQueue}")
    String gitRepoAddedQueue;
    @Value("${rabbitmq.hooks.git.repoDeletedQueue}")
    String gitRepoDeletedQueue;
    @Value("${rabbitmq.hooks.git.commitQueue}")
    String gitCommitQueue;
    @Value("${rabbitmq.hooks.docker.routingKeys.repoAdded}")
    private String dockerRepoAddedRoutingKey;
    @Value("${rabbitmq.hooks.docker.routingKeys.repoDeleted}")
    private String dockerRepoDeletedRoutingKey;
    @Value("${rabbitmq.hooks.docker.routingKeys.tag}")
    private String dockerTagRoutingKey;
    @Value("${rabbitmq.hooks.git.routingKeys.repoAdded}")
    private String gitRepoAddedRoutingKey;
    @Value("${rabbitmq.hooks.git.routingKeys.repoDeleted}")
    private String gitRepoDeletedRoutingKey;

    @Value("${rabbitmq.hooks.git.routingKeys.commit}")
    private String gitCommitRoutingKey;


    @Bean
    DirectExchange hooksExchange() {
        return new DirectExchange(hooksExchange);
    }


    @Bean
    Queue dockerRepoAddedQueue() {
        return new Queue(this.dockerRepoAddedQueue, false);
    }

    @Bean
    Queue dockerRepoDeletedQueue() {
        return new Queue(this.dockerRepoDeletedQueue, false);
    }

    @Bean
    Queue dockerTagQueue() {
        return new Queue(this.dockerTagQueue, false);
    }

    @Bean
    Queue gitRepoAddedQueue() {
        return new Queue(this.gitRepoAddedQueue, false);
    }

    @Bean
    Queue gitRepoDeletedQueue() {
        return new Queue(this.gitRepoDeletedQueue, false);
    }

    @Bean
    Queue gitCommitQueue() {
        return new Queue(this.gitCommitQueue, false);
    }


    @Bean
    Binding dockerRepoAddedBinding(Queue dockerRepoAddedQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(dockerRepoAddedQueue).to(hooksExchange).with(this.dockerRepoAddedRoutingKey);
    }

    @Bean
    Binding dockerRepoDeletedBinding(Queue dockerRepoDeletedQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(dockerRepoDeletedQueue).to(hooksExchange).with(this.dockerRepoDeletedRoutingKey);
    }

    @Bean
    Binding dockerTagBinding(Queue dockerTagQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(dockerTagQueue).to(hooksExchange).with(dockerTagRoutingKey);
    }

    @Bean
    Binding gitRepoAddedBinding(Queue gitRepoAddedQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(gitRepoAddedQueue).to(hooksExchange).with(this.gitRepoAddedRoutingKey);
    }

    @Bean
    Binding gitRepoDeletedBinding(Queue gitRepoDeletedQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(gitRepoDeletedQueue).to(hooksExchange).with(this.gitRepoDeletedRoutingKey);
    }

    @Bean
    Binding gitCommitBinding(Queue gitCommitQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(gitCommitQueue).to(hooksExchange).with(gitCommitRoutingKey);
    }

}
