package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.domain.TripleStore;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class BenchmarkJobService {

    @Value("${docker.hostPort}")
    private int hostPort;

    private final DockerContainerService containerService;
    private final IguanaService iguanaService;

    public BenchmarkJobService(DockerContainerService containerService, IguanaService iguanaService) {
        this.containerService = containerService;
        this.iguanaService = iguanaService;
    }

    public DockerContainer handleNewDockerBenchmarkJob(DockerBenchmarkJob job) throws IOException {

        TripleStore tripleStore = job.getRepo().getTripleStore();

        DataSet dataSet = job.getBenchmark().getDataSet();

        String owner = job.getRepo().getRepoOwner();
        String repoName = job.getRepo().getRepoName();
        String tagName = job.getTagName();

        DockerContainer container = this.containerService.getDockerContainer(owner, repoName, tagName);
        setContainerArguments(tripleStore, dataSet, container);

        container = this.containerService.pullImage(container);
        log.info("Container Image pulled: {}", container);
        container = this.containerService.startContainer(container);
        log.info("Container started: {}", container);

        this.iguanaService.startBenchmark(job);

        return container;
    }

    private void setContainerArguments(TripleStore tripleStore, DataSet dataSet, DockerContainer container) {
        container.setExposedPort(tripleStore.getExposedPort());
        container.setHostPort(this.hostPort);

        // TODO file in start command; Format String?
        container.setDataSetHostPath(dataSet.getFilePath());
        container.setDataSetPath(tripleStore.getDataSetPath());

        container.setEntryPoint(tripleStore.getEntryPoint());

        log.info("Container Entity created: {}", container);
    }
}
