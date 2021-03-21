package basilisk.jobsManagingService.builders.iguanaConfiguration.task.worker;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.HttpPostTaskWorker;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class HttpPostTaskWorkerBuilder extends HttpTaskWorkerBuilder{

    @Value("${IguanaConfiguration.DefaultValues.Worker.ContentType}")
    protected String contentType;

    @Autowired
    private Environment env;

    public HttpPostTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
        this.classname=env.getProperty("IguanaConfiguration.ClassName.Worker.HttpPostWorker");
    }

    public HttpTaskWorkerBuilder setContentType(String contentType)
    {
        this.contentType=contentType;
        return this;
    }


    @Override
    public TaskWorker build() {
        return new HttpPostTaskWorker(threads,queriesFile,timeOut,fixedLatency,gaussianLatency,classname,parameterName,responseType,language,contentType);
    }
}
