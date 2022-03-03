package basilisk.jobsManagingService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqHooksConfig {
    @Value("${rabbitmq.hooks.exchange}")
    String hooksExchange;

    @Value("${rabbitmq.hooks.docker.repoQueue}")
    String dockerRepoQueue;

    @Value("${rabbitmq.hooks.docker.tagQueue}")
    String dockerTagQueue;

    @Value("${rabbitmq.hooks.docker.routingKeys.repo}")
    private String dockerRepoRoutingKey;

    @Value("${rabbitmq.hooks.docker.routingKeys.tag}")
    private String dockerTagRoutingKey;


    @Value("${rabbitmq.hooks.git.repoQueue}")
    String gitRepoQueue;

    @Value("${rabbitmq.hooks.git.commitQueue}")
    String gitCommitQueue;

    @Value("${rabbitmq.hooks.git.routingKeys.repo}")
    private String gitRepoRoutingKey;

    @Value("${rabbitmq.hooks.git.routingKeys.commit}")
    private String gitCommitRoutingKey;


    @Bean
    DirectExchange hooksExchange() {
        return new DirectExchange(hooksExchange);
    }


    @Bean
    Queue dockerRepoQueue() {
        return new Queue(this.dockerRepoQueue, false);
    }

    @Bean
    Queue dockerTagQueue() {
        return new Queue(this.dockerTagQueue, false);
    }

    @Bean
    Queue gitRepoQueue() {
        return new Queue(this.gitRepoQueue, false);
    }

    @Bean
    Queue gitCommitQueue() {
        return new Queue(gitCommitQueue, false);
    }



    @Bean
    Binding dockerRepoBinding(Queue dockerRepoQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(dockerRepoQueue).to(hooksExchange).with(this.dockerRepoRoutingKey);
    }

    @Bean
    Binding dockerTagBinding(Queue dockerTagQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(dockerTagQueue).to(hooksExchange).with(dockerTagRoutingKey);
    }

    @Bean
    Binding gitRepoBinding(Queue gitRepoQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(gitRepoQueue).to(hooksExchange).with(this.gitRepoRoutingKey);
    }

    @Bean
    Binding gitCommitBinding(Queue gitCommitQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(gitCommitQueue).to(hooksExchange).with(gitCommitRoutingKey);
    }
}
