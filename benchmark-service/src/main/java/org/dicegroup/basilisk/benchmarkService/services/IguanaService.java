package org.dicegroup.basilisk.benchmarkService.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.dicegroup.basilisk.benchmarkService.model.TripleStore;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.Benchmark;
import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.benchmarkService.model.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.model.iguana.Connection;
import org.dicegroup.basilisk.benchmarkService.model.iguana.DataSet;
import org.dicegroup.basilisk.benchmarkService.model.iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.model.iguana.storage.*;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.QueryHandler;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.Task;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.TaskConfiguration;
import org.dicegroup.basilisk.benchmarkService.model.iguana.task.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IguanaService {

    private final ObjectMapper mapper;
    private final DockerContainerService containerService;

    @Value("${iguana.iguanaPath}")
    private String iguanaPath;

    @Value("${iguana.configFileDirectory}")
    private String configFileDirectory;

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
    @Value("${docker.hostPort}")
    private int hostPort;

    @Value("${iguana.placeholders.dataSetName}")
    private String dataSetNamePlaceholder;

    @Value("${iguana.placeholders.dataSetPath}")
    private String dataSetPathPlaceholder;

    public IguanaService(ObjectMapper mapper, DockerContainerService containerService) {
        this.mapper = mapper;
        this.containerService = containerService;
    }

    public void startBenchmark(DockerContainer container, BenchmarkJob job) throws IOException, InterruptedException {
        IguanaConfiguration config = this.createConfiguration(container, job);

        File tempFile = createConfigFile(config);

        ProcessBuilder pb = new ProcessBuilder(this.shell, "-c", "java -jar " + this.iguanaPath + " " + tempFile.getAbsolutePath());
        pb.inheritIO();

        Process p = pb.start();
        int status = p.waitFor();
        log.info("process exited with status {}", status);

        assert tempFile.delete();
    }

    public IguanaConfiguration createConfiguration(DockerContainer container, BenchmarkJob job) {
        IguanaConfiguration config = new IguanaConfiguration();

        config.setDataSets(List.of(new DataSet(job.getBenchmark().getDataSet().getName())));
        config.setConnections(List.of(createConnection(container, job)));
        config.setTasks(List.of(createTask(job)));
        config.setStorages(List.of(createStorage()));
        config.setPreScriptHook(getPreScriptHook(job));

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

    private Connection createConnection(DockerContainer container, BenchmarkJob job) {
        String endpoint = "http://";

        if (this.containerService.isLocalhost()) {
            endpoint += "localhost:" + this.hostPort;
        } else {
            endpoint += container.getContainerName() + ":" + container.getExposedPort();
        }

        endpoint += job.getRepo().getTripleStore().getEndpoint();

        return Connection.builder()
                .name(job.getIguanaConnectionName())
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
                .noOfQueryMixes(bm.getNoOfQueryMixes())
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

    private String getPreScriptHook(BenchmarkJob job) {
        TripleStore ts = job.getRepo().getTripleStore();
        String formatString = ts.getPreScriptHook();

        if (formatString == null) {
            return null;
        }

        String dataSetname = new File(job.getBenchmark().getDataSet().getFilePath()).getName();
        Map<String, String> valueMap = Map.of(this.dataSetPathPlaceholder, ts.getDataSetPath(), this.dataSetNamePlaceholder, dataSetname);
        return new StringSubstitutor(valueMap).replace(formatString);
    }

}
