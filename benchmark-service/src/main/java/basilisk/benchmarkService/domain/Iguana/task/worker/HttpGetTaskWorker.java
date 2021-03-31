package basilisk.benchmarkService.domain.Iguana.task.worker;

/**
 * @author Fakhr Shaheen
 */


public class HttpGetTaskWorker extends HttpTaskWorker{
    public HttpGetTaskWorker(int threads, String queriesFile, int timeOut, int fixedLatency, int gaussianLatency, String classname, String parameterName, String responseType, String language) {
        super(threads, queriesFile, timeOut, fixedLatency, gaussianLatency, classname, parameterName, responseType, language);
    }
}
