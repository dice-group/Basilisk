package basilisk.jobsManagingService.builders.iguanaConfiguration.task.queryHandler;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.TaskQueryHandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Fakhr Shaheen
 */
public class OneLineTextQueryHandlerBuilder extends TaskQueryHandlerBuilder{

    @Autowired
    private Environment env;

    public OneLineTextQueryHandlerBuilder() {
        this.className=env.getProperty("IguanaConfiguration.ClassName.InstancesQueryHandler");
    }

    @Override
    public TaskQueryHandler build() {
        return new TaskQueryHandler(className);
    }
}
