package org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.worker;

/**
 * @author Fakhr Shaheen
 */

public class HttpPostTaskWorker extends HttpTaskWorker{

    private String contentType;

    public HttpPostTaskWorker(int threads, String queriesFile, int timeOut, int fixedLatency, int gaussianLatency, String classname, String parameterName, String responseType, String language, String contentType) {
        super(threads, queriesFile, timeOut, fixedLatency, gaussianLatency, classname, parameterName, responseType, language);
        this.contentType = contentType;
    }


}
