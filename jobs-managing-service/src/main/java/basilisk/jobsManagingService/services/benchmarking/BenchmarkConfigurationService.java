package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.domain.benchmarking.QueryConfig;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public interface BenchmarkConfigurationService {

    DataSetConfig getBenchmarkDataSetConfig(Long id);
    QueryConfig getBenchmarkQueryConfig(Long id);

    List<DataSetConfig> getAllBenchmarkDataSetConfigs();
    List<QueryConfig> getAllBenchmarkQueryConfigs();

    void addBenchmarkDataSetConfig();
    void addBenchmarkQueryConfig();
}
