package basilisk.benchmarkService.domain.Iguana.task;


import basilisk.benchmarkService.domain.BaseEntity;
import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
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
