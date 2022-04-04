package org.dicegroup.basilisk.benchmarkService.domain.Iguana;


import org.dicegroup.basilisk.benchmarkService.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Dataset extends BaseEntity {

    private String name;
    private String file;
}
