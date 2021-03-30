package basilisk.jobsManagingService.messaging;

import basilisk.jobsManagingService.services.benchmarking.BenchmarkingJobsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author Fakhr Shaheen
 */

@Component
public class RabbitMqMessageReceiver implements MessageReceiver, RabbitListenerConfigurer {

    private BenchmarkingJobsService benchmarkingJobsService;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @Override
    @RabbitListener(queues = "${hooksCheckingService.rabbitmq.dockerQueue}")
    public void receiveDockerConfig() {

    }

    @Override
    @RabbitListener(queues = "${hooksCheckingService.rabbitmq.gitQueue}")
    public void receiveGitConfig() {

    }
}
