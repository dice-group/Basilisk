package basilisk.jobsManagingService.web.messaging.hooks;

import basilisk.jobsManagingService.events.DockerTagEvent;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class HooksMessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(HooksMessageReceiver.class);
    private final BenchmarkJobService benchmarkJobService;

    public HooksMessageReceiver(BenchmarkJobService jobsService) {
        this.benchmarkJobService = jobsService;
    }

    @RabbitListener(queues = "${rabbitmq.hooks.docker.tagQueue}")
    public void receiveDockerTagEvent(DockerTagEvent dockerTagEvent) {
        this.logger.info("Docker Repo {} got Docker Tag updated Event: {}", dockerTagEvent.getRepo().getRepoName(), dockerTagEvent.getTagName());
        this.benchmarkJobService.generateBenchmarkJobs(dockerTagEvent);
    }


    @RabbitListener(queues = "${rabbitmq.hooks.git.commitQueue}")
    public void receiveGitCommitEvent(GitCommitAddedEvent gitCommitAddedEvent) {
        this.logger.info("Git commit added event: {}", gitCommitAddedEvent);
        //this.benchmarkJobService.generateBenchmarkingJobs(gitCommitAddedEvent);
    }
}
