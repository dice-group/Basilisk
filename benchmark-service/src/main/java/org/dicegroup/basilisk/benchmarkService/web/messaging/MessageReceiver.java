package org.dicegroup.basilisk.benchmarkService.web.messaging;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.JobStatus;
import org.dicegroup.basilisk.benchmarkService.domain.repo.DockerRepo;
import org.dicegroup.basilisk.benchmarkService.services.BenchmarkJobService;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortCommand;
import org.dicegroup.basilisk.events.benchmark.DockerBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.benchmark.GitBenchmarkJobCreateEvent;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
public class MessageReceiver {

    private final ModelMapper mapper;

    private final BenchmarkJobService jobService;

    public MessageReceiver(ModelMapper mapper, BenchmarkJobService jobService) {
        this.mapper = mapper;
        this.jobService = jobService;
    }

    @RabbitHandler
    public void receive(GitBenchmarkJobCreateEvent event) {
        //benchmarkingOrganizerService.startBenchmark(event);
    }

    @RabbitHandler
    public void receive(DockerBenchmarkJobCreateEvent event) throws IOException {
        log.info("Recieved DockerBenchmarkJob: {}", event);

        DockerRepo repo = this.mapper.map(event.getRepo(), DockerRepo.class);

        DockerBenchmarkJob job = DockerBenchmarkJob.builder()
                .repo(repo)
                .tagName(event.getTagName())
                .imageDigest(event.getImageDigest())
                .benchmark(this.mapper.map(event.getBenchmark(), Benchmark.class))
                .status(JobStatus.CREATED)
                .build();

        this.jobService.handleNewDockerBenchmarkJob(job);
    }

    @RabbitHandler
    public void receive(BenchmarkJobAbortCommand command) {
//        .abortBenchmark(command.getBenchmarkJobId());
    }
}
