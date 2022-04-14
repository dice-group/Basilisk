package org.dicegroup.basilisk.benchmarkService.web.messaging;

import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobAbortedEvent;
import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobFailedEvent;
import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobFinishedEvent;
import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobStartedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.benchmarks.exchange}")
    private String exchange;

    @Value("${rabbitmq.benchmarks.routingKeys.result}")
    private String benchmarkResultRoutingKey;


    public void send(BenchmarkJobStartedEvent benchmarkJobStartedEvent) {
        rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobStartedEvent);
    }

    public void send(BenchmarkJobAbortedEvent benchmarkJobAbortedEvent) {
        rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobAbortedEvent);
    }

    public void send(BenchmarkJobFinishedEvent benchmarkJobFinishedEvent) {
        rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobFinishedEvent);
    }

    public void send(BenchmarkJobFailedEvent benchmarkJobFailedEvent) {
        rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobFailedEvent);
    }
}
