package basilisk.benchmarkService.builder.iguana.worker;


import basilisk.benchmarkService.domain.Iguana.task.worker.HttpGetTaskWorker;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;

/**
 * @author Fakhr Shaheen
 */
public class HttpGetTaskWorkerBuilder extends HttpTaskWorkerBuilder{

    private final String classname="HttpGetWorker";

    public HttpGetTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
    }

    @Override
    public TaskWorker build() {

        return new HttpGetTaskWorker(threads,queriesFile,timeOut,fixedLatency,gaussianLatency,classname,parameterName,responseType,language);
    }
}
