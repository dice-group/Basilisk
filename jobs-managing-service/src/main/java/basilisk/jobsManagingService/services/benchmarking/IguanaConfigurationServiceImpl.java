package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler.OneLineTextQueryHandlerBuilder;
import basilisk.jobsManagingService.builders.iguanaConfiguration.task.worker.HttpGetTaskWorkerBuilder;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.Dataset;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.IguanaConfiguration;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.IguanaConnection;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.storage.Storage;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.IguanaTask;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.TaskWorker;
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
