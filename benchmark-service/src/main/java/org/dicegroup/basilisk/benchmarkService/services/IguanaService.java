package org.dicegroup.basilisk.benchmarkService.services;

import org.aksw.iguana.cc.controller.MainController;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.Connection;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.DataSet;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.storage.FileStorageConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.storage.NTFileStorage;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.QueryHandler;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.Task;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.TaskConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.iguana.task.Worker;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class IguanaService {

    public String startBenchmark() throws IOException {
        MainController controller = new MainController();

        IguanaConfiguration config = getExampleConfiguration();


        // temporäre files?
        controller.start("/home/fabian/dev/bachelor/Basilisk/example_benchmark/iguana-config.yml", true);

        return "started?";
    }

    public IguanaConfiguration getExampleConfiguration() {
        IguanaConfiguration config = new IguanaConfiguration();

        config.setDataSets(List.of(new DataSet("swdf")));

        config.setConnections(List.of(getExampleConnection()));

        Task task = new Task();
        task.setClassName("Stresstest");
        task.setConfiguration(getExampleTaskConfig());
        config.setTasks(List.of(task));

        NTFileStorage storage = new NTFileStorage();
        storage.setConfiguration(new FileStorageConfiguration("example_benchmark/benchmark-results.nt"));

        config.setStorages(List.of(storage));

        return config;
    }

    private TaskConfiguration getExampleTaskConfig() {
        TaskConfiguration taskConfig = new TaskConfiguration();
        taskConfig.setTimeLimit(6000L);
        taskConfig.setQueryHandler(new QueryHandler("InstanceQueryHandler"));
        taskConfig.setWorkers(List.of(getExampleWorker()));
        return taskConfig;
    }

    private Connection getExampleConnection() {
        Connection con = new Connection();
        con.setName("Tentris");
        con.setEndpoint("http://localhost:81/sparql");
        con.setVersion("latest");
        return con;
    }

    private Worker getExampleWorker() {
        Worker worker = new Worker();
        worker.setClassName("SPARQLWorker");
        worker.setThreads(1);
        worker.setQueriesFile("/home/fabian/dev/bachelor/Basilisk/example_benchmark/swdf-queries_short.txt");
        return worker;
    }


}
