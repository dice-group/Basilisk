package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.domain.TripleStore;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.repositories.BenchmarkJobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BenchmarkJobService {

    @Value("${docker.hostPort}")
    private int hostPort;

    private final DockerContainerService containerService;

    public BenchmarkJobService(DockerContainerService containerService) {
        this.containerService = containerService;
    }

    public DockerContainer handleNewDockerBenchmarkJob(DockerBenchmarkJob job) {

        TripleStore tripleStore = job.getRepo().getTripleStore();

        DataSet dataSet = job.getBenchmark().getDataSet();

        String owner = job.getRepo().getRepoOwner();
        String repoName = job.getRepo().getRepoName();
        String tagName = job.getTagName();


        DockerContainer container = this.containerService.getDockerContainer(owner, repoName, tagName);

        container.setExposedPort(tripleStore.getExposedPort());
        container.setHostPort(this.hostPort);

        container.setDataSetHostPath(dataSet.getFilePath());
        container.setDataSetPath(tripleStore.getDataSetPath());

        container.setEntryPoint(tripleStore.getEntryPoint());

        log.info("Container Entity created: {}", container);

        container = this.containerService.pullImage(container);
        log.info("Container Image pulled: {}", container);

        container = this.containerService.startContainer(container);
        log.info("Container started: {}", container);


        return container;
    }
}
