package basilisk.benchmarkService.domain.Iguana.task.queryHandler;

import basilisk.benchmarkService.domain.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class IguanaTaskQueryHandler extends BaseEntity {
    protected String className;

    public IguanaTaskQueryHandler(String className) {
        this.className = className;
    }
}

