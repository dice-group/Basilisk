package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.repositories.BenchmarkJobRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BenchmarkJobService {

    private final BenchmarkJobRepository benchmarkJobRepository;

    private final DockerContainerService containerService;

    public BenchmarkJobService(BenchmarkJobRepository benchmarkJobRepository, DockerContainerService containerService) {
        this.benchmarkJobRepository = benchmarkJobRepository;
        this.containerService = containerService;
    }

    public void handleNewDockerBenchmarkJob(DockerBenchmarkJob job) {
        String owner = job.getRepo().getRepoOwner();
        String repoName = job.getRepo().getRepoName();
        String tagName = job.getTagName();
        DockerContainer container = this.containerService.getDockerContainer(owner, repoName, tagName);

        log.info("Container Entity created: {}", container);

        container = this.containerService.pullImage(container);
        log.info("Container Image pulled: {}", container);

        this.containerService.startContainer(container);
        log.info("Container started: {}", container);

    }
}
