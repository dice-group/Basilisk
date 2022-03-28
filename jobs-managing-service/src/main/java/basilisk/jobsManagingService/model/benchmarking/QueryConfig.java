package basilisk.jobsManagingService.model.benchmarking;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "QUERY_CONFIG")
@SuperBuilder
@NoArgsConstructor
public class QueryConfig extends Config {

    public QueryConfig(String name, String url) {
        super(name, url);
    }

}
