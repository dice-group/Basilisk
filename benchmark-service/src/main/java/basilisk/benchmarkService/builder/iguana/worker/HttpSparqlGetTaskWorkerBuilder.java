package basilisk.benchmarkService.builder.iguana.worker;

/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlGetTaskWorkerBuilder extends  HttpGetTaskWorkerBuilder{

    public HttpSparqlGetTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
        this.classname=env.getProperty("IguanaConfiguration.ClassName.Worker.SPARQLWorker");
    }
}
