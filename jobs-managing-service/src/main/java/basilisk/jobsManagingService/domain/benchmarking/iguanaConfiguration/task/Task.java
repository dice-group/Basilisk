package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import basilisk.jobsManagingService.domain.BaseEntity;
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
