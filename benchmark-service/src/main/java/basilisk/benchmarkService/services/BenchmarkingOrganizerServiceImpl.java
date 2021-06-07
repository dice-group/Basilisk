package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.Iguana.Dataset;
import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import basilisk.benchmarkService.domain.TripleStore;
import basilisk.benchmarkService.domain.benchamrking.BenchmarkJob;
import basilisk.benchmarkService.events.BenchmarkJobStartedEvent;
import basilisk.benchmarkService.exception.MessageSendingExecption;
import basilisk.benchmarkService.web.messaging.MessageSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@Service
public class BenchmarkingOrganizerServiceImpl implements BenchmarkingOrganizerService {

    private IguanaConfigurationService iguanaConfigurationService;
    private BenchmarkingService benchmarkingService;
    private ResultsStorageService resultsStorageService;
    private MessageSender messageSender;

    public BenchmarkingOrganizerServiceImpl(IguanaConfigurationService iguanaConfigurationService,
                                            BenchmarkingService benchmarkingService,
                                            ResultsStorageService resultsStorageService,
                                            MessageSender messageSender) {
        this.iguanaConfigurationService = iguanaConfigurationService;
        this.benchmarkingService = benchmarkingService;
        this.messageSender=messageSender;
    }


    @Override
    public void startBenchmark(BenchmarkJob benchmarkJob) {
        // create iguana configuration for the job
        Dataset dataset=new Dataset(benchmarkJob.getDataSetConfig().getName(),benchmarkJob.getDataSetConfig().getUrl());
        List<String> queryFiles=new ArrayList<>();
        // get triple store connection settings from benchmark job.
        TripleStore tripleStore=benchmarkJob.getTripleStore();
        IguanaConnection iguanaConnection=IguanaConnection.builder().
                endpoint(tripleStore.getEndpoint()).
                name(tripleStore.getName()).
                updateEndpoint(tripleStore.getUpdateEndpoint()).
                build();
        // get query files urls from benchmark job.
        benchmarkJob.getQueryConfigs().forEach(a ->{queryFiles.add(a.getUrl());});

        //build iguana configuration for the job.
        IguanaConfiguration iguanaConfiguration=iguanaConfigurationService.createDefaultIguanaConfiguration(
                iguanaConnection,
                dataset,
                resultsStorageService.getDefaultBenchmarkStorage(),
                queryFiles
                );

        String iguanaJsonFile=iguanaConfigurationService.serializeConfigurationIntoJSON(iguanaConfiguration);
        //publish an event that benchmark has been started.
        BenchmarkJobStartedEvent benchmarkJobStartedEvent=new BenchmarkJobStartedEvent(benchmarkJob.getId());
        try {
            messageSender.send(benchmarkJobStartedEvent);
        } catch (MessageSendingExecption messageSendingExecption) {
           //ToDo
        }
        // perform the benchmark
        //ToDo should be done in async
        benchmarkingService.performBenchmark(iguanaJsonFile);

    }

    @Override
    public void abortBenchmark(Long jobId) {

    }
}
