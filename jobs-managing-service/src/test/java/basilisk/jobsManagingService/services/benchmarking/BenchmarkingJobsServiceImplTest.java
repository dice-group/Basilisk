package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.GitJobConfig;
import basilisk.jobsManagingService.domain.TripleStore;
import basilisk.jobsManagingService.domain.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.domain.benchmarking.GitBenchmarkJob;
import basilisk.jobsManagingService.domain.benchmarking.JobStatus;
import basilisk.jobsManagingService.domain.benchmarking.QueryConfig;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.repositories.benchmarking.JobsRepository;
import basilisk.jobsManagingService.services.TripleStoreService;
import basilisk.jobsManagingService.web.messaging.MessageSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Fakhr Shaheen
 */

@ExtendWith(MockitoExtension.class)
class BenchmarkingJobsServiceImplTest {

    BenchmarkingJobsServiceImpl benchmarkingJobsService;

    @Mock
    private BenchmarkConfigurationService benchmarkConfigurationService;

    @Mock
    private TripleStoreService tripleStoreService;

    @Mock
    private MessageSender messageSender;

    @Mock
    private JobsRepository jobsRepository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        benchmarkingJobsService=new BenchmarkingJobsServiceImpl(benchmarkConfigurationService,tripleStoreService,messageSender,jobsRepository);
    }

    @Test
    void generateGitBenchmarkingJob() {

        //given


        GitCommitAddedEvent gitCommitAddedEvent=GitCommitAddedEvent.builder()
                .url("https://test.com")
                .commit_sha1("8bde8f3ca718ebad91893a958a2a308ff0e8286s")
                .commitCreationDate(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30))
                .repoId(1)
                .build();

        GitJobConfig gitJobConfig=GitJobConfig.builder()
                .commit_sha1(gitCommitAddedEvent.getCommit_sha1())
                .url(gitCommitAddedEvent.getUrl())
                .commitCreationDate(gitCommitAddedEvent.getCommitCreationDate())
                .build();

        TripleStore tripleStore=TripleStore.builder()
                .name("tentris")
                .endpoint("8090")
                .requiresAuthentication(false)
                .build();

        String queryName="query1";
        String queryUrl="query1Url";
        QueryConfig queryConfig=new QueryConfig(queryName,queryUrl);

        given(benchmarkConfigurationService.getAllActiveBenchmarkQueryConfigs())
                .willReturn(List.of(queryConfig));


        String dataset1Name="dataset1";
        String dataset1Url="dataset1Url";
        DataSetConfig dataSetConfig1=new DataSetConfig(dataset1Name,dataset1Url);
        String dataset2Name="dataset2";
        String dataset2Url="dataset2Url";
        DataSetConfig dataSetConfig2=new DataSetConfig(dataset2Name,dataset2Url);

        GitBenchmarkJob benchmarkJob=GitBenchmarkJob.builder()
                .gitJobConfig(gitJobConfig)
                .queryConfigs(List.of(queryConfig))
                .status(JobStatus.CREATED)
                .dataSetConfig(dataSetConfig1)
                .tripleStore(tripleStore)
                .build();

        given(benchmarkConfigurationService.getAllActiveBenchmarkDataSetConfigs())
                .willReturn(List.of(dataSetConfig1,dataSetConfig2));

        given(tripleStoreService.getTripleStoreByGitRepoId(1l))
                .willReturn(Optional.of(tripleStore));


        //when

         benchmarkingJobsService.generateBenchmarkingJobs(gitCommitAddedEvent);

        verify(jobsRepository, times(1)).save(benchmarkJob);


        //ToDo refactor
        //then
        /*Jobs.stream().forEach( job->
        {
            assertThat(job.getDataSetConfig()).isIn(dataSetConfig1,dataSetConfig2);
            assertThat(job.getQueryConfigs()).isEqualTo(List.of(queryConfig));
            GitJobConfig gitJobConfig=job.getGitJobConfig();
            assertThat(gitJobConfig.getCommit_sha1()).isEqualTo("8bde8f3ca718ebad91893a958a2a308ff0e8286s");
            assertThat(gitJobConfig.getUrl()).isEqualTo("https://test.com");
            assertThat(gitJobConfig.getCommitCreationDate()).isEqualTo(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30));
            TripleStore foundedTripleStore=job.getTripleStore();
            assertThat(foundedTripleStore.getName()).isEqualTo(tripleStore.getName());
            assertThat(foundedTripleStore.getEndpoint()).isEqualTo(tripleStore.getEndpoint());
            assertThat(foundedTripleStore.isRequiresAuthentication()).isEqualTo(tripleStore.isRequiresAuthentication());
        });*/






    }

}