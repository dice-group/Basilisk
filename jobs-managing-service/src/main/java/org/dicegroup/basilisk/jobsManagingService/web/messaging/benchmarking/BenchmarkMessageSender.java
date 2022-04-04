package org.dicegroup.basilisk.jobsManagingService.web.messaging.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.events.benchmarking.BenchmarkJobAbortCommand;
import org.dicegroup.basilisk.jobsManagingService.events.benchmarking.DockerBenchmarkJobCreatedEvent;
import org.dicegroup.basilisk.jobsManagingService.events.benchmarking.GitBenchmarkJobCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class BenchmarkMessageSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.benchmarks.exchange}")
    private String benchmarkExchange;

    @Value("${rabbitmq.benchmarks.routingKeys.job}")
    private String benchmarkJobRoutingKey;

    public void send(DockerBenchmarkJobCreatedEvent event) {
        this.rabbitTemplate.convertAndSend(this.benchmarkExchange, this.benchmarkJobRoutingKey, event);
    }

    public void send(GitBenchmarkJobCreatedEvent event) {
        this.rabbitTemplate.convertAndSend(this.benchmarkExchange, this.benchmarkJobRoutingKey, event);
    }

    public void send(BenchmarkJobAbortCommand command) {
        this.rabbitTemplate.convertAndSend(this.benchmarkExchange, this.benchmarkJobRoutingKey, command);
    }
}
