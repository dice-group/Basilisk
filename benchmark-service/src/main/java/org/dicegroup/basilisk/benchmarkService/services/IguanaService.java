package org.dicegroup.basilisk.benchmarkService.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.iguana.Connection;
import org.dicegroup.basilisk.benchmarkService.model.iguana.DataSet;
import org.dicegroup.basilisk.benchmarkService.model.iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.model.iguana.storage.*;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.QueryHandler;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.Task;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.TaskConfiguration;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.Worker;
import org.dicegroup.basilisk.benchmarkService.model.repo.Repo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class IguanaService {

    private final ObjectMapper mapper;

    @Value("${iguana.iguanaPath}")
    private String iguanaPath;

    @Value("${iguana.configFileDirectory}")
    private String configFileDirectory;

    @Value("${docker.hostPort}")
    private int hostPort;

    @Value("${iguana.resultStorage.className}")
    private String resultStorageClassName;
    @Value("${iguana.resultStorage.configuration.endpoint:#{null}}")
    private String resultStorageEndpoint;
    @Value("${iguana.resultStorage.configuration.updateEndpoint:#{null}}")
    private String resultStorageUpdateEndpoint;
    @Value("${iguana.resultStorage.configuration.fileName:#{null}}")
    private String resultsFile;

    @Value("${iguana.shell}")
    private String shell;

    @Value("${docker.hostGateway}")
    private String hostGateway;


    public IguanaService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void startBenchmark(BenchmarkJob job) throws IOException, InterruptedException {
        IguanaConfiguration config = this.createConfiguration(job);

        File tempFile = createConfigFile(config);

        ProcessBuilder pb = new ProcessBuilder(this.shell, "-c", "java -jar " + this.iguanaPath + "iguana-3.3.0.jar " + tempFile.getAbsolutePath());
        pb.inheritIO();
        pb.directory(new File(this.iguanaPath));

        Process p = pb.start();
        int status = p.waitFor();
        log.info("process exited with status {}", status);

        assert tempFile.delete();
    }

    public IguanaConfiguration createConfiguration(BenchmarkJob job) {
        IguanaConfiguration config = new IguanaConfiguration();

        config.setDataSets(List.of(new DataSet(job.getBenchmark().getDataSet().getName())));

        config.setConnections(List.of(createConnection(job.getRepo())));

        config.setTasks(List.of(createTask(job)));

        // TODO temp file for results
        config.setStorages(List.of(createStorage()));

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
        String endpoint = "http://" + this.hostGateway + ":" + this.hostPort + repo.getTripleStore().getEndpoint();

        return Connection.builder().name(repo.getRepoName()).endpoint(endpoint).build();
    }

    private Task createTask(BenchmarkJob job) {
        return Task.builder().className("Stresstest").configuration(createTaskConfig(job.getBenchmark())).build();
    }

    private TaskConfiguration createTaskConfig(Benchmark bm) {
        return TaskConfiguration.builder().timeLimit(bm.getTaskTimeLimit()).queryHandler(new QueryHandler("InstancesQueryHandler")).workers(List.of(createWorker(bm))).build();
    }

    private Worker createWorker(Benchmark bm) {
        return Worker.builder().className("SPARQLWorker").threads(bm.getWorkerThreadCount()).queriesFile(bm.getQueryFilePath()).timeOut(bm.getWorkerTimeOut()).build();
    }

    private Storage createStorage() {
        switch (this.resultStorageClassName) {
            case "TriplestoreStorage":
                return createTriplestoreStorage();
            case "NTFileStorage":
                return createNTFileStorage();
        }

        log.error("Could not find '{}' storage class!", this.resultStorageClassName);
        return createNTFileStorage();
    }

    private TriplestoreStorage createTriplestoreStorage() {
        return new TriplestoreStorage(new TriplestoreConfiguration(this.resultStorageEndpoint, this.resultStorageUpdateEndpoint));
    }

    private NTFileStorage createNTFileStorage() {
        return new NTFileStorage(new FileStorageConfiguration(this.resultsFile));
    }

}
