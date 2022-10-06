package org.dicegroup.basilisk.repositoryService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqJobsConfig {
    @Value("${rabbitmq.benchmarks.exchange}")
    String benchmarkExchange;

    @Value("${rabbitmq.benchmarks.jobQueue}")
    String benchmarkJobQueue;
    @Value("${rabbitmq.benchmarks.resultQueue}")
    String benchmarkResultQueue;

    @Value("${rabbitmq.benchmarks.routingKeys.job}")
    private String benchmarkJobQueueRoutingKey;
    @Value("${rabbitmq.benchmarks.routingKeys.result}")
    private String benchmarkResultQueueRoutingKey;


    @Bean
    DirectExchange benchmarkExchange() {
        return new DirectExchange(this.benchmarkExchange);
    }


    @Bean
    Queue benchmarkJobQueue() {
        return new Queue(this.benchmarkJobQueue, false);
    }

    @Bean
    Queue benchmarkResultQueue() {
        return new Queue(this.benchmarkResultQueue, false);
    }


    @Bean
    Binding benchmarkJobBinding(Queue benchmarkJobQueue, DirectExchange benchmarkExchange) {
        return BindingBuilder.bind(benchmarkJobQueue).to(benchmarkExchange).with(this.benchmarkJobQueueRoutingKey);
    }

    @Bean
    Binding benchmarkResultBinding(Queue benchmarkResultQueue, DirectExchange benchmarkExchange) {
        return BindingBuilder.bind(benchmarkResultQueue).to(benchmarkExchange).with(this.benchmarkResultQueueRoutingKey);
    }
}
