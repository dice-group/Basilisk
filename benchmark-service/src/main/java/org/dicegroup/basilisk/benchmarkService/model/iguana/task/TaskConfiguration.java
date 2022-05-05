package org.dicegroup.basilisk.benchmarkService.model.iguana.task;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TaskConfiguration {

    private Long timeLimit;
    private Integer noOfQueryMixes;

    private QueryHandler queryHandler;

    private List<Worker> workers;

}
