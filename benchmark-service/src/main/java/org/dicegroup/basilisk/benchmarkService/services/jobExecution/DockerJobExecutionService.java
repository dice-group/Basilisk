package org.dicegroup.basilisk.benchmarkService.services.jobExecution;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.dicegroup.basilisk.benchmarkService.model.TripleStore;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.services.DockerContainerService;
import org.dicegroup.basilisk.benchmarkService.services.IguanaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class DockerJobExecutionService implements ExecutionService {

    private final DockerContainerService containerService;
    private final IguanaService iguanaService;

    @Value("${docker.hostPort}")
    private int hostPort;

    @Value("${iguana.placeholders.dataSetName}")
    private String dataSetNamePlaceholder;

    @Value("${iguana.placeholders.dataSetPath}")
    private String dataSetPathPlaceholder;

    public DockerJobExecutionService(DockerContainerService containerService, IguanaService iguanaService) {
        this.containerService = containerService;
        this.iguanaService = iguanaService;
    }

    @Override
    public void executeBenchmarkJob(BenchmarkJob benchmarkJob) throws IOException, InterruptedException {
        DockerBenchmarkJob job = (DockerBenchmarkJob) benchmarkJob;

        job.setIguanaConnectionName(getImageName(job));

        DockerContainer container = this.containerService.getDockerContainer(job.getRepo().getRepoOwner(), job.getRepo().getRepoName(), job.getTagName());
        setContainerArguments(container, job);

        container = this.containerService.pullImage(container);
        log.info("Container Image pulled: {}", container);
        container = this.containerService.startContainer(container);
        log.info("Container started: {}", container);

        log.info("sleeping 5 seconds..");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("starting benchmark");

        this.iguanaService.startBenchmark(container, job);

        this.containerService.stopContainer(container);
        log.info("Container stoppend and removed");

        this.containerService.deleteImage(container);
        log.info("Container image removed: {}", container);
    }

    private void setContainerArguments(DockerContainer container, DockerBenchmarkJob job) {
        TripleStore ts = job.getRepo().getTripleStore();
        DataSet ds = job.getBenchmark().getDataSet();

        container.setExposedPort(ts.getExposedPort());
        container.setHostPort(this.hostPort);

        container.setDataSetHostPath(getDatasetHostPath(ds));
        container.setDataSetPath(ts.getDataSetPath());

        container.setEntryPoint(getEntrypoint(ts, ds));
    }

    private String getDatasetHostPath(DataSet dataSet) {
        return new File(dataSet.getFilePath()).getParent();
    }

    private String getEntrypoint(TripleStore tripleStore, DataSet dataSet) {
        String dataSetName = new File(dataSet.getFilePath()).getName();
        Map<String, String> valueMap = Map.of(this.dataSetPathPlaceholder, tripleStore.getDataSetPath(), this.dataSetNamePlaceholder, dataSetName);

        return new StringSubstitutor(valueMap).replace(tripleStore.getEntryPoint());
    }

    private String getImageName(DockerBenchmarkJob job) {
        return job.getRepo().getRepoOwner() + "/" + job.getRepo().getRepoName() + ":" + job.getTagName();
    }

}
