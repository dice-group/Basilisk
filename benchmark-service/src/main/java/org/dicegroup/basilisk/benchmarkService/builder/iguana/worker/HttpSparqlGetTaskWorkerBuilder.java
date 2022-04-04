package org.dicegroup.basilisk.benchmarkService.builder.iguana.worker;



/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlGetTaskWorkerBuilder extends  HttpGetTaskWorkerBuilder{

    private final String classname="SPARQLWorker";

    public HttpSparqlGetTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
    }
}
