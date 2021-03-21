package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker;

/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlUpdateTaskWorker extends HttpPostTaskWorker{

    private TimerStrategy timerStrategy;

    public HttpSparqlUpdateTaskWorker(int threads, String queriesFile, int timeOut, int fixedLatency, int gaussianLatency, String classname, String parameterName, String responseType, String language, String contentType, TimerStrategy timerStrategy) {
        super(threads, queriesFile, timeOut, fixedLatency, gaussianLatency, classname, parameterName, responseType, language, contentType);
        this.timerStrategy = timerStrategy;
    }

}
