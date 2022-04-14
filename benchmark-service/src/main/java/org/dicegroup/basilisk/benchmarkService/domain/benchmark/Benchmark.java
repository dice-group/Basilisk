package org.dicegroup.basilisk.benchmarkService.domain.benchmark;

import lombok.Getter;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Benchmark extends BaseEntity {

    private String name;
    private String queryFilePath;
    @ManyToOne
    private DataSet dataSet;

    private Integer taskTimeLimit;
    private Integer workerThreadCount;
    private Integer workerTimeOut;

}
