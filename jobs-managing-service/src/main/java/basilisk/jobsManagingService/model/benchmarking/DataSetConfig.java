package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


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
