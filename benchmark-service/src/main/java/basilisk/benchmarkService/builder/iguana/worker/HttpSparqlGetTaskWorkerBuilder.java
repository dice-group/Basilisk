package basilisk.benchmarkService.builder.iguana.worker;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlGetTaskWorkerBuilder extends  HttpGetTaskWorkerBuilder{

    @Value("${IguanaConfiguration.ClassName.Worker.SPARQLWorker}")
    private String classname;

    public HttpSparqlGetTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
    }
}
