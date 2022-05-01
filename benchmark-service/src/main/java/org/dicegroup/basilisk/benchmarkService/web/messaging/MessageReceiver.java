package org.dicegroup.basilisk.benchmarkService.web.messaging;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.repo.DockerRepo;
import org.dicegroup.basilisk.benchmarkService.services.BenchmarkJobService;
import org.dicegroup.basilisk.dto.benchmark.JobStatus;
import org.dicegroup.basilisk.events.benchmark.BenchmarkJobAbortCommand;
import org.dicegroup.basilisk.events.benchmark.DockerBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.events.benchmark.GitBenchmarkJobCreateEvent;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RabbitListener(queues = "${rabbitmq.benchmarks.jobQueue}")
public class MessageReceiver {

    private final ModelMapper mapper;

    private final BenchmarkJobService benchmarkJobService;

    public MessageReceiver(ModelMapper mapper, BenchmarkJobService benchmarkJobService) {
        this.mapper = mapper;
        this.benchmarkJobService = benchmarkJobService;
    }

    @RabbitHandler
    public void receive(GitBenchmarkJobCreateEvent event) {
        // Todo: pull repo, build image from Dockerfile
    }

    @RabbitHandler
    public void receive(DockerBenchmarkJobCreateEvent event) {
        log.info("Recieved DockerBenchmarkJob: {}", event);

        DockerRepo repo = this.mapper.map(event.getRepo(), DockerRepo.class);

        DockerBenchmarkJob job = DockerBenchmarkJob.builder()
                .id(event.getJobId())
                .repo(repo)
                .tagName(event.getTagName())
                .imageDigest(event.getImageDigest())
                .benchmark(this.mapper.map(event.getBenchmark(), Benchmark.class))
                .status(JobStatus.CREATED)
                .build();

        this.benchmarkJobService.addDockerBenchmarkJob(job);
    }

    @RabbitHandler
    public void receive(BenchmarkJobAbortCommand command) {
        log.info("recieved abort command for job: {}", command.getBenchmarkJobId());
        this.benchmarkJobService.abortJob(command.getBenchmarkJobId());
    }
}
