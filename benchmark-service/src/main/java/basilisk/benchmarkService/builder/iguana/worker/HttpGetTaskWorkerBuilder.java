package basilisk.benchmarkService.builder.iguana.worker;


import basilisk.benchmarkService.domain.Iguana.task.worker.HttpGetTaskWorker;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class HttpGetTaskWorkerBuilder extends HttpTaskWorkerBuilder{

    @Autowired
    protected Environment env;

    public HttpGetTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
        this.classname=env.getProperty("IguanaConfiguration.ClassName.Worker.HttpGetWorker");
    }

    @Override
    public TaskWorker build() {

        return new HttpGetTaskWorker(threads,queriesFile,timeOut,fixedLatency,gaussianLatency,classname,parameterName,responseType,language);
    }
}