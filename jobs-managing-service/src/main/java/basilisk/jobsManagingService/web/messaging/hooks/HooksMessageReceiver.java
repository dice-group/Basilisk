package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.DockerTagUpdatedEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkingJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class HooksMessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(HooksMessageReceiver.class);
    private final BenchmarkingJobsService benchmarkingJobsService;

    public HooksMessageReceiver(BenchmarkingJobsService jobsService) {
        this.benchmarkingJobsService = jobsService;
    }

    @RabbitListener(queues = "${rabbitmq.hooks.docker.tagQueue}")
    public void receiveDockerTagEvent(DockerTagUpdatedEvent dockerTagUpdatedEvent) {
        this.logger.info("Docker Tag updated Event: {}", dockerTagUpdatedEvent.getName());
    }


    @RabbitListener(queues = "${rabbitmq.hooks.git.commitQueue}")
    public void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent) {
        this.logger.info("Git commit added event: {}", gitCommitAddedEvent);
        this.benchmarkingJobsService.generateBenchmarkingJobs(gitCommitAddedEvent);
    }
}
