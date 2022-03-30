package basilisk.benchmarkService.domain.Iguana.storage;


import basilisk.benchmarkService.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Getter
@Entity
@NoArgsConstructor
public abstract class Storage extends BaseEntity {

    private String className;

    public Storage(String className) {
        this.className = className;
    }
}
