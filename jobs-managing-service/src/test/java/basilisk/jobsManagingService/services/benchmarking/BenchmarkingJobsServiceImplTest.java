package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.model.GitJobConfig;
import basilisk.jobsManagingService.model.benchmarking.TripleStore;
import basilisk.jobsManagingService.model.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import basilisk.jobsManagingService.model.benchmarking.JobStatus;
import basilisk.jobsManagingService.model.benchmarking.QueryConfig;
import basilisk.jobsManagingService.events.GitCommitAddedEvent;
import basilisk.jobsManagingService.repositories.benchmarking.JobsRepository;
import basilisk.jobsManagingService.web.messaging.benchmarking.BenchmarkMessageSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BenchmarkingJobsServiceImplTest {

    BenchmarkingJobsService benchmarkingJobsService;

    @Mock
    private QueryConfigService qcService;
    @Mock
    private DataSetConfigService dcService;

    @Mock
    private TripleStoreService tripleStoreService;

    @Mock
    private BenchmarkMessageSender messageSender;

    @Mock
    private JobsRepository jobsRepository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        benchmarkingJobsService=new BenchmarkingJobsService(qcService, tripleStoreService,messageSender,jobsRepository, dcService);
    }

    @Test
    void generateGitBenchmarkingJob() {

        //given


        GitCommitAddedEvent gitCommitAddedEvent=GitCommitAddedEvent.builder()
                .url("https://test.com")
                .commit_sha1("8bde8f3ca718ebad91893a958a2a308ff0e8286s")
                .repoId(1)
                .build();

        GitJobConfig gitJobConfig=GitJobConfig.builder()
                .commit_sha1(gitCommitAddedEvent.getCommit_sha1())
                .url(gitCommitAddedEvent.getUrl())
                .build();

        TripleStore tripleStore=TripleStore.builder()
                .name("tentris")
                .endpoint("8090")
                .requiresAuthentication(false)
                .build();

        String queryName="query1";
        String queryUrl="query1Url";
        QueryConfig queryConfig=new QueryConfig(queryName,queryUrl);

        given(qcService.getAllActiveQueryConfigs())
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

        given(dcService.getAllActiveDataSetConfigs())
                .willReturn(List.of(dataSetConfig1,dataSetConfig2));

//        given(tripleStoreService.getTripleStoreByGitRepoId(1L))
//                .willReturn(Optional.of(tripleStore));


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