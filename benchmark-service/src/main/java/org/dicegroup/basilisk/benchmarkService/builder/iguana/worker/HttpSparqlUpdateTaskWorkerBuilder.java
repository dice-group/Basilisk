package org.dicegroup.basilisk.benchmarkService.builder.iguana.worker;


import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker.HttpSparqlUpdateTaskWorker;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker.TimerStrategy;

/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlUpdateTaskWorkerBuilder extends HttpPostTaskWorkerBuilder{

    private TimerStrategy timerStrategy=TimerStrategy.NONE;

    private final String classname="UPDATEWorker";

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
