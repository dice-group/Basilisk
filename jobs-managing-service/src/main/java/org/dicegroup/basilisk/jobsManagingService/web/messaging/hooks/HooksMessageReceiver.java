package org.dicegroup.basilisk.jobsManagingService.web.messaging.hooks;

import org.dicegroup.basilisk.events.hooks.hook.DockerTagEvent;
import org.dicegroup.basilisk.events.hooks.hook.GitCommitEvent;
import org.dicegroup.basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${rabbitmq.hooks.hookEventQueue}")
public class HooksMessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(HooksMessageReceiver.class);
    private final BenchmarkJobService benchmarkJobService;

    public HooksMessageReceiver(BenchmarkJobService jobsService) {
        this.benchmarkJobService = jobsService;
    }

    @RabbitHandler
    public void receiveDockerTagEvent(DockerTagEvent dockerTagEvent) {
        this.logger.info("Docker Repo got Docker Tag updated Event: {}", dockerTagEvent);
        this.benchmarkJobService.generateBenchmarkJobs(dockerTagEvent);
    }


    @RabbitHandler
    public void receiveGitCommitEvent(GitCommitEvent event) {
        this.logger.info("Git commit added event: {}", event);
        //this.benchmarkJobService.generateBenchmarkingJobs(gitCommitAddedEvent);
    }
}
