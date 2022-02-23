package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class QueryConfig extends BaseEntity {

    String name;
    String url;
    boolean active;

    public QueryConfig(String name,String url)
    {
        this.name=name;
        this.url=url;
    }

}