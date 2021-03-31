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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Fakhr Shaheen
 */

@Component
public class IguanaConfigurationServiceImpl implements IguanaConfigurationService{

    @Autowired
    protected Environment env;


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
        int threadsnumber=Integer.parseInt(env.getProperty("IguanaConfiguration.DefaultConfiguration.Threads"));
        int workerTimeOut=Integer.parseInt(env.getProperty("IguanaConfiguration.DefaultConfiguration.Task.Worker.TimeOut"));
        TaskWorker worker=new HttpGetTaskWorkerBuilder(threadsnumber,"")
                .setTimeOut(workerTimeOut)
                .build();
        List<TaskWorker> workers=new ArrayList<>();
        workers.add(worker);
        //create the task handler
        IguanaTaskQueryHandler queryHandler=new OneLineTextQueryHandlerBuilder().build();

        //create the iguana task
        int restrictionAmount=Integer.parseInt(env.getProperty("IguanaConfiguration.DefaultConfiguration.Task.RestrictionAmount"));
        IguanaTask iguanaTask=IguanaTask.builder()
                .queryHandler(queryHandler)
                .workers(workers)
                .restrictionType(env.getProperty("IguanaConfiguration.DefaultConfiguration.Task.RestrictionType"))
                .restrictionAmount(restrictionAmount)
                .build();

        List<IguanaTask> iguanaTasks=new ArrayList<>();
        iguanaTasks.add(iguanaTask);
        return iguanaTasks;
    }


    private List<String> getDefaultIguanaMetrics() {

        List<String> iguanaMetrics=new ArrayList<>();
        iguanaMetrics.add(env.getProperty("IguanaConfiguration.DefaultConfiguration.Metrics[0]"));
        iguanaMetrics.add(env.getProperty("IguanaConfiguration.DefaultConfiguration.Metrics[1]"));
        iguanaMetrics.add(env.getProperty("IguanaConfiguration.DefaultConfiguration.Metrics[2]"));
        iguanaMetrics.add(env.getProperty("IguanaConfiguration.DefaultConfiguration.Metrics[3]"));
        iguanaMetrics.add(env.getProperty("IguanaConfiguration.DefaultConfiguration.Metrics[4]"));

        return iguanaMetrics;
    }

}
