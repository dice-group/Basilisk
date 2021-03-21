package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.TaskQueryHandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.TaskWorker;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public class Task extends BaseEntity {


    @Value("IguanaConfiguration.ClassName.TestType")
    private String className;

    private StressTestType stressTestType;

    private TaskQueryHandler queryHandler;
    private List<TaskWorker> workers;


}
