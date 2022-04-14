package org.dicegroup.basilisk.benchmarkService.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aksw.iguana.cc.controller.MainController;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.domain.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.Connection;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.DataSet;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.storage.FileStorageConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.storage.NTFileStorage;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.QueryHandler;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.Task;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.TaskConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.Worker;
import org.dicegroup.basilisk.benchmarkService.domain.repo.Repo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class IguanaService {

    private final ObjectMapper mapper;

    @Value("${iguana.configFileDirectory}")
    private String configFileDirectory;
    @Value("${iguana.resultsFile}")
    private String resultsFile;

    @Value("${docker.hostPort}")
    private int hostPort;

    public IguanaService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void startBenchmark(BenchmarkJob job) throws IOException {
        IguanaConfiguration config = this.createConfiguration(job);

        File tempFile = createConfigFile(config);

        MainController controller = new MainController();
        controller.start(tempFile.getAbsolutePath(), true);

        assert tempFile.delete();
    }

    public IguanaConfiguration createConfiguration(BenchmarkJob job) {
        IguanaConfiguration config = new IguanaConfiguration();

        config.setDataSets(List.of(new DataSet(job.getBenchmark().getDataSet().getName())));

        config.setConnections(List.of(createConnection(job.getRepo())));

        config.setTasks(List.of(createTask(job)));

        // TODO temp file for results!
        config.setStorages(List.of(createNTFileStorage()));

        return config;
    }

    private File createConfigFile(IguanaConfiguration config) throws IOException {
        File configFileDir = new File(this.configFileDirectory);
        assert configFileDir.mkdirs();

        File tempFile = File.createTempFile("iguana-config-", ".json", configFileDir);
        tempFile.deleteOnExit();

        this.mapper.writeValue(tempFile, config);
        return tempFile;
    }

    private Connection createConnection(Repo repo) {
        String endpoint = "http://localhost:"
                + hostPort
                + repo.getTripleStore().getEndpoint();

        return Connection.builder()
                .name(repo.getRepoName())
                .endpoint(endpoint)
                .build();
    }

    private Task createTask(BenchmarkJob job) {
        return Task.builder()
                .className("Stresstest")
                .configuration(createTaskConfig(job.getBenchmark()))
                .build();
    }

    private TaskConfiguration createTaskConfig(Benchmark bm) {
        return TaskConfiguration.builder()
                .timeLimit(bm.getTaskTimeLimit())
                .queryHandler(new QueryHandler("InstancesQueryHandler"))
                .workers(List.of(createWorker(bm)))
                .build();
    }

    private Worker createWorker(Benchmark bm) {
        return Worker.builder()
                .className("SPARQLWorker")
                .threads(bm.getWorkerThreadCount())
                .queriesFile(bm.getQueryFilePath())
                .timeOut(bm.getWorkerTimeOut())
                .build();
    }

    private NTFileStorage createNTFileStorage() {
        return new NTFileStorage(new FileStorageConfiguration(this.resultsFile));
    }

}
