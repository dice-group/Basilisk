package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.model.benchmarking.QueryConfig;
import basilisk.jobsManagingService.exception.ConfigNameAlreadyExistsException;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkDataSetConfigRepository;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkQueryConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class BenchmarkConfigurationServiceImplTest {

    @Mock
    private BenchmarkQueryConfigRepository benchmarkQueryConfigRepository;
    @Mock
    private BenchmarkDataSetConfigRepository benchmarkDataSetConfigRepository;

    private BenchmarkConfigurationService  benchmarkConfigurationService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        benchmarkConfigurationService=new BenchmarkConfigurationService(benchmarkQueryConfigRepository,benchmarkDataSetConfigRepository);
    }

    @Test
    void shouldGetBenchmarkDataSetConfig() {
        //when
        benchmarkConfigurationService.getBenchmarkDataSetConfig(1L);

        //then
        verify(benchmarkDataSetConfigRepository).findById(1L);

    }

    @Test
    void shouldGetBenchmarkQueryConfig() {
        //when
        benchmarkConfigurationService.getBenchmarkQueryConfig(1L);

        //then
        verify(benchmarkQueryConfigRepository).findById(1L);
    }

    @Test
    void shouldGetAllBenchmarkDataSetConfigs() {
        //when
        benchmarkConfigurationService.getAllBenchmarkDataSetConfigs();

        //then
        verify(benchmarkDataSetConfigRepository).findAll();
    }

    @Test
    void shouldGetAllBenchmarkQueryConfigs() {
        //when
        benchmarkConfigurationService.getAllBenchmarkQueryConfigs();

        //then
        verify(benchmarkQueryConfigRepository).findAll();
    }

    @Test
    void shouldAddBenchmarkDataSetConfig() {
        String datasetName="dataset1";
        String datasetUrl="dataset1Url";
        DataSetConfig dataSetConfig=new DataSetConfig(datasetName,datasetUrl);

        //given
        DataSetConfig emptyDataSetConfig=null;
        given(benchmarkDataSetConfigRepository.findByName(datasetName))
                .willReturn(Optional.ofNullable(emptyDataSetConfig));

        //when
        try {
            benchmarkConfigurationService.addBenchmarkDataSetConfig(datasetName,datasetUrl);
        } catch (ConfigNameAlreadyExistsException e) {
        }
        //then
        ArgumentCaptor<DataSetConfig>  dataSetConfigArgumentCaptor=ArgumentCaptor.forClass(DataSetConfig.class);

        verify(benchmarkDataSetConfigRepository).save(dataSetConfigArgumentCaptor.capture());

        DataSetConfig capturedDataSetConfig= dataSetConfigArgumentCaptor.getValue();

        assertThat(capturedDataSetConfig.getName()).isEqualTo(dataSetConfig.getName());

    }

    @Test
    void shouldNotAddBenchmarkDataSetConfig() {
        String datasetName="dataset1";
        String datasetUrl="dataset1Url";
        DataSetConfig dataSetConfig=new DataSetConfig(datasetName,datasetUrl);

        //given
        given(benchmarkDataSetConfigRepository.findByName(datasetName))
                .willReturn(Optional.of(dataSetConfig));

        //when
        try {
            benchmarkConfigurationService.addBenchmarkDataSetConfig(datasetName,datasetUrl);
        } catch (ConfigNameAlreadyExistsException e) {
        }

        //then
        assertThatThrownBy(()->benchmarkConfigurationService.addBenchmarkDataSetConfig(datasetName,datasetUrl))
                .isInstanceOf(ConfigNameAlreadyExistsException.class);

    }

    @Test
    void shouldAddBenchmarkQueryConfig() {
        String queryName="query1";
        String queryUrl="query1Url";
        QueryConfig queryConfig=new QueryConfig(queryName,queryUrl);

        //given
        QueryConfig emptyQueryConfig=null;
        given(benchmarkQueryConfigRepository.findByName(queryName))
                .willReturn(Optional.ofNullable(emptyQueryConfig));

        //when
        try {
            benchmarkConfigurationService.addBenchmarkQueryConfig(queryName,queryUrl);
        } catch (ConfigNameAlreadyExistsException e) {
        }
        //then
        ArgumentCaptor<QueryConfig>  queryConfigArgumentCaptor=ArgumentCaptor.forClass(QueryConfig.class);

        verify(benchmarkQueryConfigRepository).save(queryConfigArgumentCaptor.capture());

        QueryConfig capturedQueryConfig= queryConfigArgumentCaptor.getValue();

        assertThat(capturedQueryConfig.getName()).isEqualTo(queryConfig.getName());
    }

    @Test
    void shouldNotAddBenchmarkQueryConfig() {
        String queryName="query1";
        String queryUrl="query1url";
        QueryConfig queryConfig=new QueryConfig(queryName,queryUrl);

        //given
        given(benchmarkQueryConfigRepository.findByName(queryName))
                .willReturn(Optional.of(queryConfig));

        //when
        try {
            benchmarkConfigurationService.addBenchmarkDataSetConfig(queryName,queryUrl);
        } catch (ConfigNameAlreadyExistsException e) {
        }

        //then
        assertThatThrownBy(()->benchmarkConfigurationService.addBenchmarkQueryConfig(queryName,queryUrl))
                .isInstanceOf(ConfigNameAlreadyExistsException.class);
    }

    @Test
    void shouldReturnActiveDatasets()
    {
        DataSetConfig dataSetConfig1=new DataSetConfig("dataSetConfig1","dataSetConfig1url");
        DataSetConfig dataSetConfig2=new DataSetConfig("dataSetConfig2","dataSetConfig2url");

        //given
        given(benchmarkDataSetConfigRepository.findAllByActive(true))
                .willReturn(List.of(dataSetConfig1,dataSetConfig2));

        //when
        var dataSetConfigs=benchmarkConfigurationService.getAllActiveBenchmarkDataSetConfigs();
        //then
        assertThat(dataSetConfigs.size()==2);

    }

    @Test
    void shouldReturnActiveQueries()
    {
        QueryConfig queryConfig1=new QueryConfig("query1","query1url");
        QueryConfig queryConfig2=new QueryConfig("query2","query1ur2");

        //given
        given(benchmarkQueryConfigRepository.findAllByActive(true))
                .willReturn(List.of(queryConfig1,queryConfig2));

        //when
        var queryConfigs=benchmarkConfigurationService.getAllActiveBenchmarkQueryConfigs();
        //then
        assertThat(queryConfigs.size()==2);
    }
}