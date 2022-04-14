package org.dicegroup.basilisk.benchmarkService.domain.iguana.task;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TaskConfiguration {

    private Integer timeLimit;

    private QueryHandler queryHandler;

    private List<Worker> workers;

}
