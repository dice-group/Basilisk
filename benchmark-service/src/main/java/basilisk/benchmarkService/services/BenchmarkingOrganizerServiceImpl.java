package basilisk.benchmarkService.services;

import basilisk.benchmarkService.domain.Iguana.Dataset;
import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import basilisk.benchmarkService.domain.TripleStore;
import basilisk.benchmarkService.domain.benchamrking.BenchmarkJob;
import basilisk.benchmarkService.domain.benchamrking.QueryConfig;
import basilisk.benchmarkService.events.BenchmarkJobStartedEvent;
import basilisk.benchmarkService.exception.MessageSendingExecption;
import basilisk.benchmarkService.web.messaging.MessageSender;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
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
        // check whether dataset is already downloaded, and if not, download it.
        processFile(dataset.getName(),dataset.getFile());

        // check whether query files are already downloaded, and if not, download them.
        benchmarkJob.getQueryConfigs().forEach(f->{processFile(f.getName(),f.getUrl());});

        // perform the benchmark
        //ToDo should be done in async
        benchmarkingService.performBenchmark(iguanaJsonFile);

    }

    @Override
    public void abortBenchmark(Long jobId) {

    }


    /**
     * check whether a file is already downloaded, and if not, download it.
     * @param filename
     * @param fileURL
     */
    private void processFile(String filename,String fileURL) throws IOException {
        // check whether the file is already downloaded
        File datasetFile = new File(filename);
        boolean downloaded = datasetFile.exists();
        // if it is not downloaded, download it
        if(!downloaded)
        {
                InputStream in = new URL(fileURL).openStream();
                ReadableByteChannel readableByteChannel = Channels.newChannel(in);
                FileOutputStream fileOutputStream = null;
                fileOutputStream = new FileOutputStream(filename);
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel()
                        .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        }
    }


}
