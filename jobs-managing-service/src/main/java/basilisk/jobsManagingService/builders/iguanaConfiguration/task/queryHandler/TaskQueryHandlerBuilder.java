package basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.TaskQueryHandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.TaskWorker;

/**
 * @author Fakhr Shaheen
 */
public abstract class TaskQueryHandlerBuilder {

    protected String className;

    public abstract TaskQueryHandler build();
}
