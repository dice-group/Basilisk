package basilisk.jobsManagingService.model.benchmarking;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DATASET_CONFIG")
@NoArgsConstructor
public class DataSetConfig extends Config {

    public DataSetConfig(String name, String url) {
        super(name, url);
    }

}
