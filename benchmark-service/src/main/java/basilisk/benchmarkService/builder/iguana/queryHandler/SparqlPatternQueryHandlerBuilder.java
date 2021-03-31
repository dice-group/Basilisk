package basilisk.benchmarkService.builder.iguana.queryHandler;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.benchmarkService.domain.Iguana.task.queryHandler.SparqlPatternQueryHandlerIguana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class SparqlPatternQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {

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
    public IguanaTaskQueryHandler build() {
        return new SparqlPatternQueryHandlerIguana(className,endpoint,limit);
    }
}
