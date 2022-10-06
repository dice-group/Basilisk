package org.dicegroup.basilisk.repositoryService.model.benchmarking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.repositoryService.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Benchmark extends BaseEntity {

    private String name;
    private String queryFilePath;

    @ManyToOne
    private DataSet dataSet;

    private Long taskTimeLimit;
    private Integer noOfQueryMixes;
    private Integer workerThreadCount;
    private Long workerTimeOut;

}
