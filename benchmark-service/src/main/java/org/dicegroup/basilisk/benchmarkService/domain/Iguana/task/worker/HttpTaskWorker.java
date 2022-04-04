package org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker;

/**
 * @author Fakhr Shaheen
 */


public abstract class HttpTaskWorker extends TaskWorker{
    public HttpTaskWorker(int threads, String queriesFile, int timeOut, int fixedLatency, int gaussianLatency, String classname, String parameterName, String responseType, String language) {
        super(threads, queriesFile, timeOut, fixedLatency, gaussianLatency, classname);
        this.parameterName = parameterName;
        this.responseType = responseType;
        this.language = language;
    }

    protected String parameterName;
    protected String responseType;
    protected String language;

}
