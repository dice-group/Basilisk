package org.dicegroup.basilisk.benchmarkService.services;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.dicegroup.basilisk.benchmarkService.model.TripleStore;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.DockerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
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

    private final RabbitListenerEndpointRegistry rabbitRegistry;

    @Value("${docker.hostPort}")
    private int hostPort;

    @Value("${iguana.placeholders.dataSetName}")
    private String dataSetNamePlaceholder;

    @Value("${iguana.placeholders.dataSetPath}")
    private String dataSetPathPlaceholder;

    private boolean isRunning;

    public BenchmarkJobService(DockerContainerService containerService, IguanaService iguanaService, RabbitListenerEndpointRegistry rabbitRegistry) {
        this.containerService = containerService;
        this.iguanaService = iguanaService;
        this.rabbitRegistry = rabbitRegistry;

        start();
    }

    public boolean isRunning() {
        log.info("rabbit registry status: {}", this.rabbitRegistry.isRunning());
        return this.isRunning;
    }

    public void start() {
        this.isRunning = true;
        this.rabbitRegistry.start();
    }

    public void stop() {
        this.isRunning = false;
        this.rabbitRegistry.stop();
    }

    public DockerContainer handleNewDockerBenchmarkJob(DockerBenchmarkJob job) throws IOException, InterruptedException {

        TripleStore tripleStore = job.getRepo().getTripleStore();

        DataSet dataSet = job.getBenchmark().getDataSet();

        DockerContainer container = this.containerService.getDockerContainer(job.getRepo().getRepoOwner(), job.getRepo().getRepoName(), job.getTagName());
        setContainerArguments(tripleStore, dataSet, container);

        container = this.containerService.pullImage(container);
        log.info("Container Image pulled: {}", container);
        container = this.containerService.startContainer(container);
        log.info("Container started: {}", container);

        log.info("sleeping 5 seconds..");
        Thread.sleep(5000);
        log.info("starting benchmark");

        this.iguanaService.startBenchmark(container, job);

        container = this.containerService.stopContainer(container);
        log.info("Container stoppend and removed");

        // TODO write result to Fuseki

        return container;
    }

    private void setContainerArguments(TripleStore tripleStore, DataSet dataSet, DockerContainer container) {
        container.setExposedPort(tripleStore.getExposedPort());
        container.setHostPort(this.hostPort);

        container.setDataSetHostPath(getDatasetHostPath(dataSet));
        container.setDataSetPath(tripleStore.getDataSetPath());

        container.setEntryPoint(getEntrypoint(tripleStore, dataSet));
    }

    private String getDatasetHostPath(DataSet dataSet) {
        return new File(dataSet.getFilePath()).getParent();
    }

    private String getEntrypoint(TripleStore tripleStore, DataSet dataSet) {
        String dataSetName = new File(dataSet.getFilePath()).getName();
        Map<String, String> valueMap = Map.of(this.dataSetPathPlaceholder, tripleStore.getDataSetPath(), this.dataSetNamePlaceholder, dataSetName);

        return new StringSubstitutor(valueMap).replace(tripleStore.getEntryPoint());
    }

}
