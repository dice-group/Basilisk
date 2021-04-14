package basilisk.benchmarkService.domain.Iguana.task;


import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import basilisk.benchmarkService.domain.Iguana.task.worker.TaskWorker;
import lombok.*;

import java.util.List;


/**
 * @author Fakhr Shaheen
 */

@Setter
@Getter
@Builder
public class IguanaTask {

    private final String classname="Stresstest";

    private String restrictionType;
    private int restrictionAmount;
    private IguanaTaskQueryHandler queryHandler;
    private List<TaskWorker> workers;


}
