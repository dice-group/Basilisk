package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataSet extends BaseEntity {

    String name;
    String filePath;

}
