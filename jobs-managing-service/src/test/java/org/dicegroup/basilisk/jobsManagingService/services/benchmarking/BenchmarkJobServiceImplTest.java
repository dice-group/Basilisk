package org.dicegroup.basilisk.jobsManagingService.services.benchmarking;

import org.dicegroup.basilisk.events.hooks.hook.GitCommitEvent;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.DataSet;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.JobStatus;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.TripleStore;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.BenchmarkJobRepository;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.DockerBenchmarkJobRepository;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.GitBenchmarkJobRepository;
import org.dicegroup.basilisk.jobsManagingService.services.repo.DockerRepoService;
import org.dicegroup.basilisk.jobsManagingService.web.messaging.benchmarking.BenchmarkMessageSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BenchmarkJobServiceImplTest {

    BenchmarkJobService benchmarkJobService;

    @Mock
    private DockerRepoService repoService;

    @Mock
    private BenchmarkService benchmarkService;
    @Mock
    private DataSetService dcService;

    @Mock
    private TripleStoreService tripleStoreService;

    @Mock
    private BenchmarkMessageSender messageSender;

    @Mock
    private BenchmarkJobRepository benchmarkJobRepository;
    @Mock
    private DockerBenchmarkJobRepository dockerBenchmarkJobRepository;
    @Mock
    private GitBenchmarkJobRepository gitBenchmarkJobRepository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        benchmarkJobService = new BenchmarkJobService(repoService, benchmarkJobRepository, tripleStoreService, messageSender, dockerBenchmarkJobRepository, gitBenchmarkJobRepository, benchmarkService, dcService, mapper);
    }

    @Test
    void generateGitBenchmarkingJob() {

        //given


        GitCommitEvent gitCommitAddedEvent = GitCommitEvent.builder()
                .url("https://test.com")
                .commitSha1("8bde8f3ca718ebad91893a958a2a308ff0e8286s")
                .repoId(1L)
                .build();

        TripleStore tripleStore = TripleStore.builder()
                .name("tentris")
                .endpoint("8090")
                .requiresAuthentication(false)
                .build();

        String queryName = "query1";
        String queryUrl = "query1Url";
//        QueryConfig queryConfig=new QueryConfig(queryName,queryUrl);

//        given(qcService.getAllActiveQueryConfigs())
//                .willReturn(List.of(queryConfig));


        String dataset1Name = "dataset1";
        String dataset1Url = "dataset1Url";
        DataSet dataSet1 = new DataSet(dataset1Name, dataset1Url);
        String dataset2Name = "dataset2";
        String dataset2Url = "dataset2Url";
        DataSet dataSet2 = new DataSet(dataset2Name, dataset2Url);

        GitBenchmarkJob benchmarkJob = GitBenchmarkJob.builder()
                .status(JobStatus.CREATED)
                .build();

        given(dcService.getAllDataSets())
                .willReturn(List.of(dataSet1, dataSet2));

//        given(tripleStoreService.getTripleStoreByGitRepoId(1L))
//                .willReturn(Optional.of(tripleStore));


        //when

        benchmarkJobService.generateBenchmarkingJobs(gitCommitAddedEvent);

        verify(this.gitBenchmarkJobRepository, times(1)).save(benchmarkJob);


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