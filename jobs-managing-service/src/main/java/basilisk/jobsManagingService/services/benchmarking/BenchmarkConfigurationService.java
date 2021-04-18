package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.domain.benchmarking.QueryConfig;
import basilisk.jobsManagingService.exception.ConfigNameAlreadyExistsException;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public interface BenchmarkConfigurationService {

    DataSetConfig getBenchmarkDataSetConfig(Long id);
    QueryConfig getBenchmarkQueryConfig(Long id);

    List<DataSetConfig> getAllBenchmarkDataSetConfigs();
    List<QueryConfig> getAllBenchmarkQueryConfigs();

    DataSetConfig addBenchmarkDataSetConfig(String datasetFileName,String datasetFileUrl) throws ConfigNameAlreadyExistsException;
    QueryConfig addBenchmarkQueryConfig(String queryFileName,String queryFileUrl) throws ConfigNameAlreadyExistsException;
}
