package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.domain.benchmarking.QueryConfig;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkDataSetConfigRepository;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkQueryConfigRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Component
public class BenchmarkConfigurationServiceImpl implements BenchmarkConfigurationService {


    BenchmarkQueryConfigRepository benchmarkQueryConfigRepository;
    BenchmarkDataSetConfigRepository benchmarkDataSetConfigRepository;

    public BenchmarkConfigurationServiceImpl(BenchmarkQueryConfigRepository benchmarkQueryConfigRepository, BenchmarkDataSetConfigRepository benchmarkDataSetConfigRepository) {
        this.benchmarkQueryConfigRepository = benchmarkQueryConfigRepository;
        this.benchmarkDataSetConfigRepository = benchmarkDataSetConfigRepository;
    }

    @Override
    public DataSetConfig getBenchmarkDataSetConfig(Long id) {
        var config= benchmarkDataSetConfigRepository.findById(id);
        if(config.isPresent())
            return config.get();
        else
            return null;
    }

    @Override
    public QueryConfig getBenchmarkQueryConfig(Long id) {
        var config= benchmarkQueryConfigRepository.findById(id);
        if(config.isPresent())
            return config.get();
        else
            return null;
    }

    @Override
    public List<DataSetConfig> getAllBenchmarkDataSetConfigs() {
        return (List<DataSetConfig>) benchmarkDataSetConfigRepository.findAll();
    }

    @Override
    public List<QueryConfig> getAllBenchmarkQueryConfigs() {
        return (List<QueryConfig>) benchmarkQueryConfigRepository.findAll();
    }

    @Override
    public void addBenchmarkDataSetConfig() {

    }

    @Override
    public void addBenchmarkQueryConfig() {

    }
}
