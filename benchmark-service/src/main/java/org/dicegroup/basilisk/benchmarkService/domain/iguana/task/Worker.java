package org.dicegroup.basilisk.benchmarkService.domain.iguana.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Worker {

    private String className;
    private Integer threads;
    private String queriesFile;

    private Integer fixedLatency;

    private Integer timeOut;

}
