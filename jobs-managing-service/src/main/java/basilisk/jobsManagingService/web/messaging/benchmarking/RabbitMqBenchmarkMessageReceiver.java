package basilisk.jobsManagingService.web.messaging.benchmarking;

import basilisk.jobsManagingService.events.benchmarking.*;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkingJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqBenchmarkMessageReceiver implements RabbitListenerConfigurer {

    private final BenchmarkingJobsService benchmarkingJobsService;

    private final Logger logger = LoggerFactory.getLogger(RabbitMqBenchmarkMessageReceiver.class);

    public RabbitMqBenchmarkMessageReceiver(BenchmarkingJobsService benchmarkingJobsService) {
        this.benchmarkingJobsService = benchmarkingJobsService;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
    public void receive(BenchmarkJobStartedEvent benchmarkJobStartedEvent) {
        benchmarkingJobsService.setJobStatusAsStarted(benchmarkJobStartedEvent.getJobId());
    }
}
