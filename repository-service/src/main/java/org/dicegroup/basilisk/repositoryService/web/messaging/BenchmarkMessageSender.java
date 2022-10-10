package org.dicegroup.basilisk.repositoryService.web.messaging;

import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortCommand;
import org.dicegroup.basilisk.events.benchmark.DockerBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.benchmark.GitBenchmarkJobCreateEvent;
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

    public void send(DockerBenchmarkJobCreateEvent event) {
        this.rabbitTemplate.convertAndSend(this.benchmarkExchange, this.benchmarkJobRoutingKey, event);
    }

    public void send(GitBenchmarkJobCreateEvent event) {
        this.rabbitTemplate.convertAndSend(this.benchmarkExchange, this.benchmarkJobRoutingKey, event);
    }

    public void send(BenchmarkJobAbortCommand command) {
        this.rabbitTemplate.convertAndSend(this.benchmarkExchange, this.benchmarkJobRoutingKey, command);
    }
}
