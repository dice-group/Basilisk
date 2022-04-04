package org.dicegroup.basilisk.benchmarkService.builder.iguana.queryHandler;


import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import org.dicegroup.basilisk.benchmarkService.domain.Iguana.task.queryHandler.SparqlPatternQueryHandlerIguana;

/**
 * @author Fakhr Shaheen
 */
public class SparqlPatternQueryHandlerBuilder extends IguanaTaskQueryHandlerBuilder {

    private String endpoint;

    private int limit=2000;

    private final String className="PatternQueryHandler";


    public SparqlPatternQueryHandlerBuilder(String endpoint) {
        this.endpoint=endpoint;
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
