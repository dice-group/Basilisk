package basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.SparqlPatternQueryHandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.TaskQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class SparqlPatternQueryHandlerBuilder extends TaskQueryHandlerBuilder{

    private String endpoint;
    @Value("${IguanaConfiguration.DefaultValues.Queryhandler.SparqlPatternTimeLimit}")
    private int limit;

    @Autowired
    private Environment env;


    public SparqlPatternQueryHandlerBuilder(String endpoint) {
        this.endpoint=endpoint;
        this.className=env.getProperty("IguanaConfiguration.ClassName.PatternQueryHandler");
    }

    public SparqlPatternQueryHandlerBuilder setLimit(int limit)
    {
        this.limit=limit;
        return this;
    }
    @Override
    public TaskQueryHandler build() {
        return new SparqlPatternQueryHandler(className,endpoint,limit);
    }
}
