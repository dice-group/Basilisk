package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler;

public class SparqlPatternQueryHandlerIguana extends IguanaTaskQueryHandler {

    private String endpoint;
    private int limit;

    public SparqlPatternQueryHandlerIguana(String className, String endpoint, int limit) {
        super(className);
        this.endpoint = endpoint;
        this.limit = limit;
    }
}
