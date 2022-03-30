package basilisk.benchmarkService.domain.Iguana.task;


import basilisk.benchmarkService.domain.BaseEntity;
import basilisk.benchmarkService.domain.Iguana.task.queryHandler.IguanaTaskQueryHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class IguanaTask extends BaseEntity {

    private final String classname = "Stresstest";

    private String restrictionType;
    private int restrictionAmount;
    @ManyToOne
    private IguanaTaskQueryHandler queryHandler;
//    private List<TaskWorker> workers;


}
