package org.dicegroup.basilisk.benchmarkService.services;

import org.dicegroup.basilisk.benchmarkService.web.messaging.MessageSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;


@Service
public class BenchmarkingOrganizerService {

    private final IguanaConfigurationService iguanaConfigurationService;
    private final BenchmarkingService benchmarkingService;
    private final ResultsStorageService resultsStorageService;
    private final MessageSender messageSender;

    public BenchmarkingOrganizerService(IguanaConfigurationService iguanaConfigurationService,
                                        BenchmarkingService benchmarkingService,
                                        ResultsStorageService resultsStorageService,
                                        MessageSender messageSender) {
        this.iguanaConfigurationService = iguanaConfigurationService;
        this.benchmarkingService = benchmarkingService;
        this.messageSender = messageSender;
        this.resultsStorageService = resultsStorageService;
    }


//    public void startBenchmark(BenchmarkJob benchmarkJob) {
//        // create iguana configuration for the job
//        Dataset dataset = new Dataset(benchmarkJob.getDataSet().getName(), benchmarkJob.getDataSetConfig().getUrl());
//        List<String> queryFiles = new ArrayList<>();
//        // get triple store connection settings from benchmark job.
//        TripleStore tripleStore = benchmarkJob.getTripleStore();
//        IguanaConnection iguanaConnection = IguanaConnection.builder().
//                endpoint(tripleStore.getEndpoint()).
//                name(tripleStore.getName()).
//                updateEndpoint(tripleStore.getUpdateEndpoint()).
//                build();
//        // get query files urls from benchmark job.
//        benchmarkJob.getQueryConfigs().forEach(a -> {
//            queryFiles.add(a.getUrl());
//        });
//
//        //build iguana configuration for the job.
//        IguanaConfiguration iguanaConfiguration = iguanaConfigurationService.createDefaultIguanaConfiguration(
//                iguanaConnection,
//                dataset,
//                resultsStorageService.getDefaultBenchmarkStorage(),
//                queryFiles
//        );
//
//        String iguanaJsonFile = iguanaConfigurationService.serializeConfigurationIntoJSON(iguanaConfiguration);
//        //publish an event that benchmark has been started.
//        BenchmarkJobStartedEvent benchmarkJobStartedEvent = new BenchmarkJobStartedEvent(benchmarkJob.getId());
//        try {
//            messageSender.send(benchmarkJobStartedEvent);
//        } catch (MessageSendingExecption messageSendingExecption) {
//            //ToDo
//        }
//        // check whether dataset is already downloaded, and if not, download it.
//        processFile(dataset.getName(), dataset.getFile());
//
//        // check whether query files are already downloaded, and if not, download them.
//        benchmarkJob.getQueryConfigs().forEach(f -> {
//            processFile(f.getName(), f.getUrl());
//        });
//
//        // perform the benchmark
//        //ToDo should be done in async
//        benchmarkingService.performBenchmark(iguanaJsonFile);
//
//    }

    public void abortBenchmark(Long jobId) {

    }


    private void processFile(String filename, String fileURL) {
        // check whether the file is already downloaded
        File datasetFile = new File(filename);
        boolean downloaded = datasetFile.exists();
        // if it is not downloaded, download it
        if (!downloaded) {
            InputStream in = null;
            try {
                in = new URL(fileURL).openStream();
                ReadableByteChannel readableByteChannel = Channels.newChannel(in);
                FileOutputStream fileOutputStream = null;
                fileOutputStream = new FileOutputStream(filename);
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel()
                        .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
