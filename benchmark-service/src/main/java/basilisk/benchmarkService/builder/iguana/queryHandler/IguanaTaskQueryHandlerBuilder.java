package basilisk.benchmarkService.builder.iguana.queryHandler;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;

/**
 * @author Fakhr Shaheen
 */
public abstract class IguanaTaskQueryHandlerBuilder {

    protected String className;

    public abstract IguanaTaskQueryHandler build();
}
