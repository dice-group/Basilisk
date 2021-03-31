package basilisk.jobsManagingService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fakhr Shaheen
 */

@Configuration
public class RabbitMQHooksConfig {
    @Value("${hooks.rabbitmq.docker.ImagesQueue}")
    String dockerImagesQueueName;

    @Value("${hooks.rabbitmq.docker.ReposQueue}")
    String dockerReposQueueName;

    @Value("${hooks.rabbitmq.docker.TagsQueue}")
    String dockerTagsQueueName;

    @Value("${hooks.rabbitmq.git.ReposQueue}")
    String gitReposQueueName;

    @Value("${hooks.rabbitmq.git.CommitsQueue}")
    String gitCommitsQueueName;

    @Value("${hooks.rabbitmq.exchange}")
    String exchange;

    @Value("${hooks.rabbitmq.dockerRoutingkeys.Repo}")
    private String dockerRepoRoutingkey;

    @Value("${hooks.rabbitmq.dockerRoutingkeys.Image}")
    private String dockerImageRoutingkey;

    @Value("${hooks.rabbitmq.dockerRoutingkeys.Tag}")
    private String dockerTagRoutingkey;


    @Value("${hooks.rabbitmq.gitRoutingkeys.Repo}")
    private String gitRepoRoutingkey;

    @Value("${hooks.rabbitmq.gitRoutingkeys.Commit}")
    private String gitCommitRoutingkey;

    @Bean
    Queue dockerReposQueue() {
        return new Queue(dockerReposQueueName, false);
    }

    @Bean
    Queue dockerImagesQueue() {
        return new Queue(dockerImagesQueueName, false);
    }

    @Bean
    Queue dockerTagsQueue() {
        return new Queue(dockerTagsQueueName, false);
    }

    @Bean
    Queue gitReposQueue() {
        return new Queue(gitReposQueueName, false);
    }

    @Bean
    Queue gitCommitsQueue() {
        return new Queue(gitCommitsQueueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }



    @Bean
    Binding dockerRepoBinding(Queue dockerReposQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dockerReposQueue).to(exchange).with(dockerRepoRoutingkey);
    }

    @Bean
    Binding dockerImageBinding(Queue dockerImagesQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dockerImagesQueue).to(exchange).with(dockerImageRoutingkey);
    }

    @Bean
    Binding dockerTagBinding(Queue dockerTagsQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dockerTagsQueue).to(exchange).with(dockerTagRoutingkey);
    }

    @Bean
    Binding gitRepoBinding(Queue gitReposQueue, DirectExchange exchange) {
        return BindingBuilder.bind(gitReposQueue).to(exchange).with(gitRepoRoutingkey);
    }

    @Bean
    Binding gitCommitBinding(Queue gitCommitsQueue, DirectExchange exchange) {
        return BindingBuilder.bind(gitCommitsQueue).to(exchange).with(gitCommitRoutingkey);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
