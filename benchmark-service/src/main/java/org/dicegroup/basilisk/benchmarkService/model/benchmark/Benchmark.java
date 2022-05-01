package org.dicegroup.basilisk.benchmarkService.model.benchmark;

import lombok.Getter;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Benchmark {

    @Id
    private Long id;
    private String name;
    private String queryFilePath;
    @ManyToOne(cascade = CascadeType.ALL)
    private DataSet dataSet;

    private Integer taskTimeLimit;
    private Integer workerThreadCount;
    private Integer workerTimeOut;

}
