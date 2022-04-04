package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String queryFileUrl;

    @ManyToOne
    private DataSet dataSet;

}
