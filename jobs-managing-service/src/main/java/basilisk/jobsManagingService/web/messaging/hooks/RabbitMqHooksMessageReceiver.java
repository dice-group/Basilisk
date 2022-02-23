package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.DockerTagUpdatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkingJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMqHooksMessageReceiver implements HooksMessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(RabbitMqHooksMessageReceiver.class);
    private final BenchmarkingJobsService benchmarkingJobsService;

    public RabbitMqHooksMessageReceiver(BenchmarkingJobsService jobsService) {
        this.benchmarkingJobsService = jobsService;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.hooks.docker.tagQueue}")
    public void receiveDockerTagEvent(DockerTagUpdatedEvent dockerTagUpdatedEvent) {
        this.logger.info("Docker Tag updated Event: {}", dockerTagUpdatedEvent.getName());
    }


    @Override
    @RabbitListener(queues = "${rabbitmq.hooks.git.commitQueue}")
    public void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent) {
        this.logger.info("Git commit added event: {}", gitCommitAddedEvent);
        // this.benchmarkingJobsService.generateBenchmarkingJobs(gitCommitAddedEvent);
    }
}
