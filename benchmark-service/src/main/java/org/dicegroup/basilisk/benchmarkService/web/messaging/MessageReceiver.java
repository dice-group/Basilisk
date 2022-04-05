package org.dicegroup.basilisk.benchmarkService.web.messaging;


import org.dicegroup.basilisk.benchmarkService.events.BenchmarkJobCreatedEvent;
import org.dicegroup.basilisk.benchmarkService.services.BenchmarkingOrganizerService;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;


@Component
public class MessageReceiver implements RabbitListenerConfigurer {

    private final BenchmarkingOrganizerService benchmarkingOrganizerService;

    public MessageReceiver(BenchmarkingOrganizerService benchmarkingOrganizerService) {
        this.benchmarkingOrganizerService = benchmarkingOrganizerService;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
    public void receive(BenchmarkJobCreatedEvent event) {
        benchmarkingOrganizerService.startBenchmark(event.getBenchmarkJob());
    }

    @RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
    public void receive(BenchmarkJobAbortCommand command) {
        benchmarkingOrganizerService.abortBenchmark(command.getBenchmarkJobId());
    }
}
