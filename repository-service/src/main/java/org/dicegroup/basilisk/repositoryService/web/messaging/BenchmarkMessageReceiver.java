package org.dicegroup.basilisk.repositoryService.web.messaging;

import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortedEvent;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobFinishedEvent;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobStartedEvent;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RabbitListener(queues = "${rabbitmq.benchmarks.resultQueue}")
public class BenchmarkMessageReceiver {

    private final BenchmarkJobService benchmarkJobService;

    public BenchmarkMessageReceiver(BenchmarkJobService benchmarkJobService) {
        this.benchmarkJobService = benchmarkJobService;
    }

    @RabbitHandler
    public void receive(BenchmarkJobStartedEvent event) {
        this.benchmarkJobService.setJobStatusAsStarted(event.getJobId());
    }

    @RabbitHandler
    public void receive(BenchmarkJobAbortedEvent event) {
        this.benchmarkJobService.setJobStatusAsAborted(event.getJobId());
    }

    @RabbitHandler
    public void receive(BenchmarkJobFinishedEvent event) {
        this.benchmarkJobService.setJobStatusAsFinished(event.getJobId());
    }

}
