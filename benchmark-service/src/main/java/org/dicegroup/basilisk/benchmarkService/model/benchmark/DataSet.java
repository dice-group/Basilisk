package org.dicegroup.basilisk.benchmarkService.model.benchmark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DataSet {

    @Id
    private Long Id;
    private String name;
    private String filePath;

}
