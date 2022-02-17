package basilisk.jobsManagingService.web.messaging;

import basilisk.jobsManagingService.events.*;
import basilisk.jobsManagingService.events.BenchmarkJob.*;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkingJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;

/**
 * @author Fakhr Shaheen
 */

@Service
public class RabbitMqMessageReceiver implements MessageReceiver, RabbitListenerConfigurer {

    private final BenchmarkingJobsService benchmarkingJobsService;

    private final Logger logger = LoggerFactory.getLogger(RabbitMqMessageReceiver.class);

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
        benchmarkingJobsService.generateBenchmarkingJobs(dockerImageCreatedEvent);
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
        benchmarkingJobsService.generateBenchmarkingJobs(gitCommitAddedEvent);
    }

    @Override
    @RabbitListener(queues = "${hooks.rabbitmq.git.ReposQueue}")
    public void receiveGitRepoEvent(GitRepoAddedEvent gitRepoAddedEvent) {
        logger.warn("From Queue : {} ", gitRepoAddedEvent.getRepoName());

    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.startedEventQueue}")
    public void receive(BenchmarkJobStartedEvent benchmarkJobStartedEvent) {
        benchmarkingJobsService.setJobStatusAsStarted(benchmarkJobStartedEvent.getJobId());
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.abortedEventQueue}")
    public void receive(BenchmarkJobAbortedEvent benchmarkJobAbortedEvent) {
        benchmarkingJobsService.setJobStatusAsAborted(benchmarkJobAbortedEvent.getJobId());
    }



    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.finishedEventQueue}")
    public void receive(BenchmarkJobFinishedEvent benchmarkJobFinishedEvent) {
        benchmarkingJobsService.setJobStatusAsFinished(benchmarkJobFinishedEvent.getJobId());
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.failedEventQueue}")
    public void receive(BenchmarkJobFailedEvent benchmarkJobFailedEvent) {
        benchmarkingJobsService.setJobStatusAsFailed(benchmarkJobFailedEvent.getJobId());
    }

    @Override
    @RabbitListener(queues = "${jobs.benchmark.rabbitmq.abortCommandQueue}")
    public void receive(BenchmarkJobAbortCommand benchmarkJobAbortCommand) {
        benchmarkingJobsService.abortJob(benchmarkJobAbortCommand.getJobId());
    }
}
