package basilisk.benchmarkService.builder.iguana.worker;


import basilisk.benchmarkService.domain.Iguana.task.worker.HttpPostTaskWorker;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Fakhr Shaheen
 */
public class HttpPostTaskWorkerBuilder extends HttpTaskWorkerBuilder{

    @Value("${IguanaConfiguration.DefaultValues.Worker.ContentType}")
    protected String contentType;

    @Value("${IguanaConfiguration.ClassName.Worker.HttpPostWorker}")
    private String classname;

    public HttpPostTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
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
