package org.dicegroup.basilisk.jobsManagingService.web.controllers;

import org.dicegroup.basilisk.dto.benchmark.BenchmarkDto;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;
import org.dicegroup.basilisk.events.benchmark.DockerBenchmarkJobCreateEvent;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.Benchmark;
import org.dicegroup.basilisk.jobsManagingService.model.repo.DockerRepo;
import org.dicegroup.basilisk.jobsManagingService.services.benchmarking.BenchmarkService;
import org.dicegroup.basilisk.jobsManagingService.services.repo.DockerRepoService;
import org.dicegroup.basilisk.jobsManagingService.web.messaging.benchmarking.BenchmarkMessageSender;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    private final BenchmarkMessageSender messageSender;
    private final ModelMapper mapper;

    private final DockerRepoService dockerRepoService;
    private final BenchmarkService benchmarkService;

    public TestController(BenchmarkMessageSender messageSender, ModelMapper mapper, DockerRepoService dockerRepoService, BenchmarkService benchmarkService) {
        this.messageSender = messageSender;
        this.mapper = mapper;
        this.dockerRepoService = dockerRepoService;
        this.benchmarkService = benchmarkService;
    }

    @GetMapping("/docker")
    public DockerBenchmarkJobCreateEvent sendDockerCreate() {
        DockerRepo repo = this.dockerRepoService.getAllRepos().get(0);

        Benchmark benchmark = this.benchmarkService.getAllBenchmarks().get(0);

        DockerBenchmarkJobCreateEvent event = DockerBenchmarkJobCreateEvent.builder()
                .repo(this.mapper.map(repo, DockerRepoDto.class))
                .tagName("latest")
                .imageDigest("DIGEST")
                .benchmark(this.mapper.map(benchmark, BenchmarkDto.class))
                .build();

        this.messageSender.send(event);

        return event;
    }

}
