package basilisk.jobsManagingService.builders.iguanaConfiguration.task.worker;

/**
 * @author Fakhr Shaheen
 */
public class HttpSparqlGetTaskWorkerBuilder extends  HttpGetTaskWorkerBuilder{

    public HttpSparqlGetTaskWorkerBuilder(int threads, String queriesFile) {
        super(threads, queriesFile);
        this.classname=env.getProperty("IguanaConfiguration.ClassName.Worker.SPARQLWorker");
    }
}
