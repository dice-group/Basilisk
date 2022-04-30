package org.dicegroup.basilisk.benchmarkService.web.controllers;

import org.dicegroup.basilisk.benchmarkService.model.TripleStore;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.model.repo.DockerRepo;
import org.dicegroup.basilisk.benchmarkService.services.DockerContainerService;
import org.dicegroup.basilisk.benchmarkService.services.jobExecution.DockerJobExecutionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("test")
public class TestController {

    private final DockerContainerService containerService;

    private final DockerJobExecutionService dockerJobExecutionService;

    private final String owner = "dicegroup"; // "docker"
    private final String name = "tentris_server"; // "getting-started"
    private final String tag = "latest";

    @Value("${test.queryFilePath}")
    private String queryFilePath;

    @Value("${test.datasetPath}")
    private String datasetPath;

    private DockerBenchmarkJob benchmarkJob;

    public TestController(DockerContainerService containerService, DockerJobExecutionService dockerJobExecutionService) {
        this.containerService = containerService;
        this.dockerJobExecutionService = dockerJobExecutionService;
    }

    private void createBenchmarkJob() {
        TripleStore ts = new TripleStore();
        ts.setExposedPort(9080);

        ts.setEntryPoint("-f ${dataSetPath}/${dataSetName} --logstdout");
        ts.setDataSetPath("/datasets");
        ts.setEndpoint("/sparql");

        DockerRepo repo = new DockerRepo();
        repo.setRepoName("tentris_server");
        repo.setRepoOwner("dicegroup");
        repo.setTripleStore(ts);

        DataSet ds = new DataSet();
        ds.setName("swdf");
        ds.setFilePath(this.datasetPath);

        Benchmark bm = new Benchmark();
        bm.setQueryFilePath(this.queryFilePath);
        bm.setDataSet(ds);
        bm.setTaskTimeLimit(10000);
        bm.setWorkerThreadCount(1);
        bm.setWorkerTimeOut(3000);

        this.benchmarkJob = DockerBenchmarkJob.builder()
                .repo(repo)
                .tagName("latest")
                .benchmark(bm)
                .build();
    }

    @GetMapping("/handle")
    public String handleBenchmarkJob() throws IOException, InterruptedException {
        createBenchmarkJob();
        this.dockerJobExecutionService.executeBenchmarkJob(this.benchmarkJob);
        return "Benchmarked finished";
    }

    @GetMapping("/start")
    public String startContainer() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        String id = this.containerService.startContainer(container).getContainerId();
        return "started.. with ID: " + id;
    }

    @GetMapping("/stop")
    public String stopContainer() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        container = this.containerService.stopContainer(container);
        return "stopped: " + container;
    }
}
