package org.dicegroup.basilisk.jobsManagingService.config;

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


    @Value("${rabbitmq.hooks.repoQueue}")
    String repoQueue;

    @Value("${rabbitmq.hooks.hookEventQueue}")
    String hookEventQueue;

    @Value("${rabbitmq.hooks.repoRoutingKey}")
    private String repoRoutingKey;
    @Value("${rabbitmq.hooks.hookEventRoutingKey}")
    private String hookEventRoutingKey;


    @Bean
    DirectExchange hooksExchange() {
        return new DirectExchange(hooksExchange);
    }


    @Bean
    Queue repoQueue() {
        return new Queue(this.repoQueue, false);
    }

    @Bean
    Queue hookEventQueue() {
        return new Queue(this.hookEventQueue, false);
    }


    @Bean
    Binding dockerRepoBinding(Queue repoQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(repoQueue).to(hooksExchange).with(this.repoRoutingKey);
    }

    @Bean
    Binding dockerTagBinding(Queue hookEventQueue, DirectExchange hooksExchange) {
        return BindingBuilder.bind(hookEventQueue).to(hooksExchange).with(hookEventRoutingKey);
    }
}
