package basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.IguanaTaskQueryHandler;

/**
 * @author Fakhr Shaheen
 */
public abstract class IguanaTaskQueryHandlerBuilder {

    protected String className;

    public abstract IguanaTaskQueryHandler build();
}
