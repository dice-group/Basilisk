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
@Getter
public abstract class BenchmarkJob extends BaseEntity {

    private DataSetConfig dataSetConfig;
    private List<QueryConfig> queryConfigs;
    private TripleStore tripleStore;

}
