package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class SparqlPatternQueryHandler extends TaskQueryHandler {

    private String endpoint;
    private int limit;

    @Autowired
    private Environment env;

    public SparqlPatternQueryHandler(String endpoint, int limit) {
        this.endpoint = endpoint;
        this.limit = limit;
        this.className=env.getProperty("IguanaConfiguration.ClassName.PatternQueryHandler");

    }



}
