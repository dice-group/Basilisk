package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.dicegroup.basilisk.benchmarkService.domain.TripleStore;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class BenchmarkJobService {

    private final DockerContainerService containerService;
    private final IguanaService iguanaService;
    @Value("${docker.hostPort}")
    private int hostPort;

    @Value("${iguana.placeholders.dataSetName}")
    private String dataSetNamePlaceholder;

    @Value("${iguana.placeholders.dataSetPath}")
    private String dataSetPathPlaceholder;

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

        container.setDataSetHostPath(getDatasetHostPath(dataSet));
        container.setDataSetPath(tripleStore.getDataSetPath());

        container.setEntryPoint(getEntrypoint(tripleStore, dataSet));

        log.info("Container Entity created: {}", container);
    }

    private String getDatasetHostPath(DataSet dataSet) {
        return new File(dataSet.getFilePath()).getParent();
    }

    private String getEntrypoint(TripleStore tripleStore, DataSet dataSet) {
        String dataSetName = new File(dataSet.getFilePath()).getName();
        Map<String, String> valueMap = Map.of(this.dataSetPathPlaceholder, tripleStore.getDataSetPath(), this.dataSetNamePlaceholder, dataSetName);

        return new StrSubstitutor(valueMap).replace(tripleStore.getEntryPoint());
    }

}
