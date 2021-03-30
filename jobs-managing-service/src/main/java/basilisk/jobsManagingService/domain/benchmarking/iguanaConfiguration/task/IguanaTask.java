package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.worker.TaskWorker;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Setter
@Getter
@Builder
public class IguanaTask extends BaseEntity {

    @Value("${IguanaConfiguration.ClassName.TestType")
    private String className;
    private String restrictionType;
    private int restrictionAmount;
    private IguanaTaskQueryHandler queryHandler;
    private List<TaskWorker> workers;


}
