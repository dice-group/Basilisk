package org.dicegroup.basilisk.benchmarkService.web.controllers;

import com.github.dockerjava.api.model.Image;
import org.dicegroup.basilisk.benchmarkService.domain.TripleStore;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DataSet;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.DockerBenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.repo.DockerRepo;
import org.dicegroup.basilisk.benchmarkService.services.BenchmarkJobService;
import org.dicegroup.basilisk.benchmarkService.services.DockerContainerService;
import org.dicegroup.basilisk.benchmarkService.services.IguanaService;
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

    private final DockerBenchmarkJob benchmarkJob;

    public TestController(DockerContainerService containerService, BenchmarkJobService benchmarkJobService, IguanaService iguanaService) {
        this.containerService = containerService;
        this.benchmarkJobService = benchmarkJobService;
        this.iguanaService = iguanaService;

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
        bm.setQueryFilePath("/home/fabian/dev/bachelor/Basilisk/example_benchmark/swdf-queries_short.txt");
        bm.setDataSet(ds);
        bm.setTaskTimeLimit(2000);
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
        return this.benchmarkJobService.handleNewDockerBenchmarkJob(this.benchmarkJob);
    }

    @GetMapping("/iguana")
    public String startBenchmark() throws IOException, InterruptedException {
        this.iguanaService.startBenchmark(this.benchmarkJob);

        return "started";
    }

    @GetMapping("/iguana-config")
    public IguanaConfiguration getIguanaConfig() {
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
    public ResponseEntity<List<Image>> listContainers() {
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
