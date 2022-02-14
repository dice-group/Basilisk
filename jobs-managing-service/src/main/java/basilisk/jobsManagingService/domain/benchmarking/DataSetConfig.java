package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author Fakhr Shaheen
 */

@Getter
@NoArgsConstructor
@Entity
public class DataSetConfig extends BaseEntity {

    String name;
    String url;
    boolean active;

    public DataSetConfig(String name,String url)
    {
        this.name=name;
        this.url=url;
    }

}
