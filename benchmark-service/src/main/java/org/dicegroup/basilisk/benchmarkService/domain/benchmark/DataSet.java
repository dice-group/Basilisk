package org.dicegroup.basilisk.benchmarkService.domain.benchmark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.domain.BaseEntity;

import javax.persistence.Entity;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DataSet extends BaseEntity {

    String name;
    String url;

}
