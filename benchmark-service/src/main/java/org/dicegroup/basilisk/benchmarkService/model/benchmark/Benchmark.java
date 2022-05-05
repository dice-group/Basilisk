package org.dicegroup.basilisk.benchmarkService.model.benchmark;

import lombok.Getter;
import lombok.Setter;

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

    private Long taskTimeLimit;
    private Integer noOfQueryMixes;
    private Integer workerThreadCount;
    private Long workerTimeOut;

}
