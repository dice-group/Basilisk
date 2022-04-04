package org.dicegroup.basilisk.benchmarkService.builder.iguana.worker;


import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker.HttpPostTaskWorker;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;

/**
 * @author Fakhr Shaheen
 */
public class HttpPostTaskWorkerBuilder extends HttpTaskWorkerBuilder{

    protected String contentType="text/plain";

    private final String classname="HttpPostWorker";

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
