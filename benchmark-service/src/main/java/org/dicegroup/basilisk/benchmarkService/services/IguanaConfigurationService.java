package org.dicegroup.basilisk.benchmarkService.services;


import org.dicegroup.basilisk.benchmarkService.builder.iguana.queryHandler.OneLineTextQueryHandlerBuilder;
import org.dicegroup.basilisk.benchmarkService.builder.iguana.worker.HttpGetTaskWorkerBuilder;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.Dataset;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.storage.Storage;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.IguanaTask;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class IguanaConfigurationService {


    public IguanaConfiguration createDefaultIguanaConfiguration(IguanaConnection connection, Dataset dataset, Storage storage, List<String> queryFiles) {

        List<String> iguanaMetrics = List.of("QMPH", "QPS", "NoQPH", "AvgQPS", "NoQ");
//        IguanaConfiguration iguanaConfiguration=IguanaConfiguration.builder()
//                .iguanaTasks(getDefaultIguanaTasks(queryFiles))
//                .iguanaMetrics(iguanaMetrics)
//                .iguanaConnection(connection)
//                .dataset(dataset)
//                .storage(storage)
//                .build();

        return null; // iguanaConfiguration;
    }


    public String serializeConfigurationIntoJSON(IguanaConfiguration iguanaConfiguration) {
        return null;
    }

    private List<IguanaTask> getDefaultIguanaTasks(List<String> queryFiles) {

        int threadsnumber = 1;
        int workerTimeOut = 180000;
        int restrictionAmount = 360000;
        String restrictionType = "timeLimit";

        List<IguanaTask> iguanaTasks = new ArrayList<>();

        queryFiles.stream().forEach(queryFile ->
                {


                    //create the task worker
                    TaskWorker worker = new HttpGetTaskWorkerBuilder(threadsnumber, queryFile)
                            .setTimeOut(workerTimeOut)
                            .build();
                    List<TaskWorker> workers = new ArrayList<>();
                    workers.add(worker);
                    //create the task handler
                    IguanaTaskQueryHandler queryHandler = new OneLineTextQueryHandlerBuilder().build();

                    //create the iguana task
                    IguanaTask iguanaTask = IguanaTask.builder()
                            .queryHandler(queryHandler)
//                .workers(workers)
                            .restrictionType(restrictionType)
                            .restrictionAmount(restrictionAmount)
                            .build();

                    iguanaTasks.add(iguanaTask);
                }
        );
        return iguanaTasks;
    }


}
