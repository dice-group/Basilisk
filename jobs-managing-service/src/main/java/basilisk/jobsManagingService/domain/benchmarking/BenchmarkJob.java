package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.TripleStore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@SuperBuilder
@NoArgsConstructor
public abstract class BenchmarkJob extends BaseEntity {

    private List<DataSetConfig> dataSetConfigs;
    private List<QueryConfig> queryConfigs;
    private TripleStore tripleStore;

}
