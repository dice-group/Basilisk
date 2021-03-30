package basilisk.jobsManagingService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fakhr Shaheen
 */

@Configuration
public class RabbitMQHooksConfig {

    @Value("${hooksCheckingService.rabbitmq.dockerQueue}")
    String dockerQueueName;

    @Value("${hooksCheckingService.rabbitmq.gitQueue}")
    String gitQueueName;

    @Value("${hooksCheckingService.rabbitmq.exchange}")
    String exchange;

    @Value("${hooksCheckingService.rabbitmq.dockerRoutingkey}")
    private String dockerRoutingkey;

    @Value("${hooksCheckingService.rabbitmq.gitRoutingkey}")
    private String gitRoutingkey;

    @Bean
    Queue dockerQueue() {
        return new Queue(dockerQueueName, false);
    }

    @Bean
    Queue gitQueue() {
        return new Queue(gitQueueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding dockerBinding(Queue dockerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(dockerQueue).to(exchange).with(dockerRoutingkey);
    }

    @Bean
    Binding gitBinding(Queue gitQueue, DirectExchange exchange) {
        return BindingBuilder.bind(gitQueue).to(exchange).with(gitRoutingkey);
    }

}


