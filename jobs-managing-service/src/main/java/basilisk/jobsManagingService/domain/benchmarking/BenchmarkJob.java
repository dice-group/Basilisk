package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.TripleStore;
import lombok.*;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Builder
@Setter
@Getter
public abstract class BenchmarkJob extends BaseEntity {

    private List<DataSetConfig> dataSetConfigs;
    private List<QueryConfig> queryConfigs;
    private TripleStore tripleStore;

}
