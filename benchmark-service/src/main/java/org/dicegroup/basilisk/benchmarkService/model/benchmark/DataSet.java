package org.dicegroup.basilisk.benchmarkService.model.benchmark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;

import javax.persistence.Entity;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DataSet extends BaseEntity {

    String name;
    String filePath;

}
