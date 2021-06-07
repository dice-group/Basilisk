package basilisk.benchmarkService.web.messaging;

import basilisk.benchmarkService.events.BenchmarkJobAbortCommand;
import basilisk.benchmarkService.events.BenchmarkJobCreatedEvent;
import basilisk.benchmarkService.services.BenchmarkingOrganizerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class RabbitMqMessageReceiver implements MessageReceiver, RabbitListenerConfigurer {

    private BenchmarkingOrganizerService benchmarkingOrganizerService;

    public RabbitMqMessageReceiver(BenchmarkingOrganizerService benchmarkingOrganizerService) {
        this.benchmarkingOrganizerService = benchmarkingOrganizerService;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.createdEventQueue.}")
    public void receive(BenchmarkJobCreatedEvent event) {
        benchmarkingOrganizerService.startBenchmark(event.getBenchmarkJob());
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.abortCommandQueue}")
    public void receive(BenchmarkJobAbortCommand command) {
        benchmarkingOrganizerService.abortBenchmark(command.getJobId());
    }
}
