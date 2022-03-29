package basilisk.jobsManagingService.web.messaging.benchmarking;

import basilisk.jobsManagingService.events.benchmarking.BenchmarkJobStartedEvent;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqBenchmarkMessageReceiver implements RabbitListenerConfigurer {

    private final BenchmarkJobService benchmarkJobService;

    private final Logger logger = LoggerFactory.getLogger(RabbitMqBenchmarkMessageReceiver.class);

    public RabbitMqBenchmarkMessageReceiver(BenchmarkJobService benchmarkJobService) {
        this.benchmarkJobService = benchmarkJobService;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
    public void receive(BenchmarkJobStartedEvent benchmarkJobStartedEvent) {
        benchmarkJobService.setJobStatusAsStarted(benchmarkJobStartedEvent.getJobId());
    }
}
