package basilisk.benchmarkService.builder.iguana.worker;


import basilisk.benchmarkService.domain.Iguana.task.worker.HttpSparqlUpdateTaskWorker;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import basilisk.benchmarkService.domain.Iguana.task.worker.TimerStrategy;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlUpdateTaskWorkerBuilder extends HttpPostTaskWorkerBuilder{

    @Value("${IguanaConfiguration.DefaultValues.Worker.TimerStrategy}")
    private TimerStrategy timerStrategy;

    public HttpSparqlUpdateTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
    }

    public HttpTaskWorkerBuilder setTimerStrategy(TimerStrategy timerStrategy)
    {
        this.timerStrategy=timerStrategy;
        return this;
    }

    @Override
    public TaskWorker build() {
        return new HttpSparqlUpdateTaskWorker(threads,queriesFile,timeOut,fixedLatency,gaussianLatency,classname,parameterName,responseType,language,contentType,timerStrategy);
    }

}
