package basilisk.benchmarkService.web.messaging;

import basilisk.benchmarkService.events.BenchmarkJobAbortCommand;
import basilisk.benchmarkService.events.BenchmarkJobCreatedEvent;
import basilisk.benchmarkService.services.BenchamrkingOrganizerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;

/**
 * @author Fakhr Shaheen
 */
public class RabbitMqMessageReceiver implements MessageReceiver, RabbitListenerConfigurer {

    private BenchamrkingOrganizerService benchamrkingOrganizerService;

    public RabbitMqMessageReceiver(BenchamrkingOrganizerService benchamrkingOrganizerService) {
        this.benchamrkingOrganizerService = benchamrkingOrganizerService;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.createdEventQueue.}")
    public void receive(BenchmarkJobCreatedEvent event) {
        benchamrkingOrganizerService.startBenchmark(event.getBenchmarkJob());
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.abortCommandQueue}")
    public void receive(BenchmarkJobAbortCommand command) {
        benchamrkingOrganizerService.abortBenchmark(command.getJobId());
    }
}
