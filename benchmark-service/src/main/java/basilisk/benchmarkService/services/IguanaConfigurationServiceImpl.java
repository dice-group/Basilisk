package basilisk.benchmarkService.services;


import basilisk.benchmarkService.builder.iguana.queryHandler.OneLineTextQueryHandlerBuilder;
import basilisk.benchmarkService.builder.iguana.worker.HttpGetTaskWorkerBuilder;
import basilisk.benchmarkService.domain.Iguana.Dataset;
import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import basilisk.benchmarkService.domain.Iguana.storage.Storage;
import basilisk.benchmarkService.domain.Iguana.task.IguanaTask;
import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Fakhr Shaheen
 */

@Component
public class IguanaConfigurationServiceImpl implements IguanaConfigurationService{

    @Value( "${IguanaConfiguration.DefaultConfiguration.Threads}" )
    private int threadsnumber;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Task.Worker.TimeOut}" )
    private int workerTimeOut;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Task.RestrictionAmount}" )
    private int restrictionAmount;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Task.RestrictionType}" )
    private String restrictionType;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Metrics[0]}" )
    private String metric1;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Metrics[1]}" )
    private String metric2;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Metrics[2]}" )
    private String metric3;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Metrics[3]}" )
    private String metric4;
    @Value( "${IguanaConfiguration.DefaultConfiguration.Metrics[4]}" )
    private String metric5;


    @Override
    public IguanaConfiguration createDefaultIguanaConfiguration(IguanaConnection connection, List<Dataset> datasets, List<Storage> storages){

        List<IguanaConnection> iguanaConnections=new ArrayList<>();
        iguanaConnections.add(connection);
        IguanaConfiguration iguanaConfiguration=IguanaConfiguration.builder()
                .iguanaTasks(getDefaultIguanaTasks())
                .iguanaMetrics(getDefaultIguanaMetrics())
                .iguanaConnections(iguanaConnections)
                .datasets(datasets)
                .storages(storages)
                .build();

        return iguanaConfiguration;
    }


    private List<IguanaTask> getDefaultIguanaTasks() {

        //create the task worker
        TaskWorker worker=new HttpGetTaskWorkerBuilder(threadsnumber,"")
                .setTimeOut(workerTimeOut)
                .build();
        List<TaskWorker> workers=new ArrayList<>();
        workers.add(worker);
        //create the task handler
        IguanaTaskQueryHandler queryHandler=new OneLineTextQueryHandlerBuilder().build();

        //create the iguana task
        IguanaTask iguanaTask=IguanaTask.builder()
                .queryHandler(queryHandler)
                .workers(workers)
                .restrictionType(restrictionType)
                .restrictionAmount(restrictionAmount)
                .build();

        List<IguanaTask> iguanaTasks=new ArrayList<>();
        iguanaTasks.add(iguanaTask);
        return iguanaTasks;
    }


    private List<String> getDefaultIguanaMetrics() {

        List<String> iguanaMetrics=new ArrayList<>();
        iguanaMetrics.add(metric1);
        iguanaMetrics.add(metric2);
        iguanaMetrics.add(metric3);
        iguanaMetrics.add(metric4);
        iguanaMetrics.add(metric5);

        return iguanaMetrics;
    }

}
