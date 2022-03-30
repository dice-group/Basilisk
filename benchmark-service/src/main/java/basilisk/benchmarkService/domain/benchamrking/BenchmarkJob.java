package basilisk.benchmarkService.domain.benchamrking;


import basilisk.benchmarkService.domain.BaseEntity;
import basilisk.benchmarkService.domain.TripleStore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public abstract class BenchmarkJob extends BaseEntity {

    private DataSetConfig dataSetConfig;
    private List<QueryConfig> queryConfigs;
    private TripleStore tripleStore;
    private JobStatus status;

}
