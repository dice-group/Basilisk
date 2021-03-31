package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.BaseEntity;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public abstract class BenchmarkJob extends BaseEntity {

    private List<DataSetConfig> dataSetConfigs;
    private List<QueryConfig> queryConfigs;

}
