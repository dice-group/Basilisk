package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class SparqlPatternQueryHandler extends TaskQueryHandler {

    private String endpoint;
    private int limit;

    public SparqlPatternQueryHandler(String className, String endpoint, int limit) {
        super(className);
        this.endpoint = endpoint;
        this.limit = limit;
    }
}
