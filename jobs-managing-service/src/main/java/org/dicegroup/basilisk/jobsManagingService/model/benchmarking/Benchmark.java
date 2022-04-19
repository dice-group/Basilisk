package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;

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

    private Integer taskTimeLimit;
    private Integer workerThreadCount;
    private Integer workerTimeOut;

}
