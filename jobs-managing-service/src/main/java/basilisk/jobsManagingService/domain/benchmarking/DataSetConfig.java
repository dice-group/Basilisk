package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.BaseEntity;
import lombok.Getter;

/**
 * @author Fakhr Shaheen
 */

@Getter
public class DataSetConfig extends BaseEntity {

    String name;
    String url;
    boolean isActive;

    public DataSetConfig(String name,String url)
    {
        this.name=name;
        this.url=url;
    }

}
