package org.dicegroup.basilisk.benchmarkService.web.messaging;

import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobAbortedEvent;
import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobFailedEvent;
import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobFinishedEvent;
import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobStartedEvent;
import org.dicegroup.basilisk.benchmarkService.exception.MessageSendingExecption;
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


    public void send(BenchmarkJobStartedEvent benchmarkJobStartedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobStartedEvent);
        } catch (Exception e) {
            throw new MessageSendingExecption();
        }
    }

    public void send(BenchmarkJobAbortedEvent benchmarkJobAbortedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobAbortedEvent);
        } catch (Exception e) {
            throw new MessageSendingExecption();
        }
    }

    public void send(BenchmarkJobFinishedEvent benchmarkJobFinishedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobFinishedEvent);
        } catch (Exception e) {
            throw new MessageSendingExecption();
        }
    }

    public void send(BenchmarkJobFailedEvent benchmarkJobFailedEvent) throws MessageSendingExecption {
        try {
            rabbitTemplate.convertAndSend(exchange, benchmarkResultRoutingKey, benchmarkJobFailedEvent);
        } catch (Exception e) {
            throw new MessageSendingExecption();
        }
    }
}
