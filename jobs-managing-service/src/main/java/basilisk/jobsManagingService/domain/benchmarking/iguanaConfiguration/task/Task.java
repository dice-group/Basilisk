package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import basilisk.jobsManagingService.domain.BaseEntity;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public class Task extends BaseEntity {

    private String className;
    private StressTestType stressTestType;
    private TaskQueryHandler queryHandler;
    private List<TaskWorker> workers;


}
