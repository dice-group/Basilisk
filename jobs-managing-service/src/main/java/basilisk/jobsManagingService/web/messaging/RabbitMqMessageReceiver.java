package basilisk.jobsManagingService.web.messaging;

import basilisk.jobsManagingService.events.*;
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

    public RabbitMqMessageReceiver(BenchmarkingJobsService benchmarkingJobsService) {
        this.benchmarkingJobsService = benchmarkingJobsService;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        System.out.println();
    }


    @Override
    @RabbitListener(queues = "${hooks.rabbitmq.docker.ImagesQueue}")
    public void receiveDockerImageEvent(DockerImageCreatedEvent dockerImageCreatedEvent) {
        benchmarkingJobsService.createDockerBenchmarkingJob(dockerImageCreatedEvent);
    }

    @Override
    @RabbitListener(queues = "${hooks.rabbitmq.docker.ReposQueue}")
    public void receiveDockerRepoEvent(DockerRepoAddedEvent dockerRepoAddedEvent) {
        System.out.println();
    }

    @Override
    @RabbitListener(queues = "${hooks.rabbitmq.docker.TagsQueue}")
    public void receiveDockerTagEvent(DockerTagUpdatedEvent dockerTagUpdatedEvent) {
        System.out.println();
    }

    @Override
    @RabbitListener(queues = "${hooks.rabbitmq.git.CommitsQueue}")
    public void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent) {
        benchmarkingJobsService.createGitBenchmarkingJob(gitCommitAddedEvent);
    }

    @Override
    @RabbitListener(queues = "${hooks.rabbitmq.git.ReposQueue}")
    public void receiveGitRepoEvent(GitRepoAddedEvent gitRepoAddedEvent) {
        System.out.println();

    }
}
