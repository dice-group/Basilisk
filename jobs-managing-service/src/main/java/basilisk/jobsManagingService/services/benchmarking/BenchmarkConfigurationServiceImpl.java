package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.model.benchmarking.QueryConfig;
import basilisk.jobsManagingService.exception.ConfigNameAlreadyExistsException;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkDataSetConfigRepository;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkQueryConfigRepository;
import org.springframework.stereotype.Component;

import java.util.List;


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
    public List<DataSetConfig> getAllActiveBenchmarkDataSetConfigs() {
        return benchmarkDataSetConfigRepository.findAllByActive(true);
    }

    @Override
    public List<QueryConfig> getAllActiveBenchmarkQueryConfigs() {
        return benchmarkQueryConfigRepository.findAllByActive(true);
    }

    @Override
    public DataSetConfig addBenchmarkDataSetConfig(String datasetFileName,String datasetFileUrl) throws ConfigNameAlreadyExistsException {
        var dataSetConfig=benchmarkDataSetConfigRepository.findByName(datasetFileName);
        if(dataSetConfig.isPresent())
            throw new ConfigNameAlreadyExistsException();
        else
        {
            DataSetConfig savedDataSetConfig=new DataSetConfig(datasetFileName,datasetFileUrl) ;
            benchmarkDataSetConfigRepository.save(savedDataSetConfig);
            return savedDataSetConfig;
        }

    }

    @Override
    public QueryConfig addBenchmarkQueryConfig(String queryFileName,String queryFileUrl) throws ConfigNameAlreadyExistsException {
        var queryConfig=benchmarkQueryConfigRepository.findByName(queryFileName);
        if(queryConfig.isPresent())
            throw new ConfigNameAlreadyExistsException();
        else
        {
            QueryConfig savedQueryConfig=new QueryConfig(queryFileName,queryFileUrl) ;
            benchmarkQueryConfigRepository.save(savedQueryConfig);
            return savedQueryConfig;
        }

    }
}
