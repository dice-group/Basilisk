package org.dicegroup.basilisk.benchmarkService.web.controllers;

import com.github.dockerjava.api.model.Image;
import org.dicegroup.basilisk.benchmarkService.model.TripleStore;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.model.iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.model.repo.DockerRepo;
import org.dicegroup.basilisk.benchmarkService.services.BenchmarkJobService;
import org.dicegroup.basilisk.benchmarkService.services.DockerContainerService;
import org.dicegroup.basilisk.benchmarkService.services.IguanaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    private final DockerContainerService containerService;

    private final BenchmarkJobService benchmarkJobService;

    private final IguanaService iguanaService;

    private final String owner = "dicegroup"; // "docker"
    private final String name = "tentris_server"; // "getting-started"
    private final String tag = "latest";

    @Value("${test.queryFilePath}")
    private String queryFilePath;

    private DockerBenchmarkJob benchmarkJob;

    public TestController(DockerContainerService containerService, BenchmarkJobService benchmarkJobService, IguanaService iguanaService) {
        this.containerService = containerService;
        this.benchmarkJobService = benchmarkJobService;
        this.iguanaService = iguanaService;

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
        ds.setFilePath("/home/fabian/dev/bachelor/Basilisk/example_benchmark/swdf.nt");

        Benchmark bm = new Benchmark();
        bm.setQueryFilePath(this.queryFilePath);
        bm.setDataSet(ds);
        bm.setTaskTimeLimit(5000);
        bm.setWorkerThreadCount(1);
        bm.setWorkerTimeOut(1000);

        this.benchmarkJob = DockerBenchmarkJob.builder()
                .repo(repo)
                .tagName("latest")
                .benchmark(bm)
                .build();
    }

    @GetMapping("/handle")
    public DockerContainer handleBenchmarkJob() throws IOException, InterruptedException {
        createBenchmarkJob();
        return this.benchmarkJobService.handleNewDockerBenchmarkJob(this.benchmarkJob);
    }

    @GetMapping("/iguana")
    public String startBenchmark() throws IOException, InterruptedException {
        createBenchmarkJob();
        this.iguanaService.startBenchmark(this.benchmarkJob);

        return "started";
    }

    @GetMapping("/iguana-config")
    public IguanaConfiguration getIguanaConfig() {
        createBenchmarkJob();
        return this.iguanaService.createConfiguration(this.benchmarkJob);
    }

    @GetMapping("/add")
    public DockerContainer addContainer() {
        return this.containerService.getDockerContainer(this.owner, this.name, this.tag);
    }

    @GetMapping("/containers")
    public List<DockerContainer> getContainers() {
        return this.containerService.getAllContainers();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Image>> listImages() {
        List<Image> images = this.containerService.listImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/find")
    public String findImageID() {
        return this.containerService.findImageIdByName(this.owner, this.name, this.tag);
    }

    @GetMapping("/pull")
    public String pullImage() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        return this.containerService.pullImage(container).getImageId();
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

    @GetMapping("/delete")
    public String deleteImage() {
        DockerContainer container = this.containerService.getDockerContainer(this.owner, this.name, this.tag);

        container = this.containerService.deleteImage(container);
        return "deleted: " + container;
    }
}
