package org.dicegroup.basilisk.jobsManagingService.web.messaging.benchmarking;

import lombok.extern.slf4j.Slf4j;
import org.dicegroup.basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BenchmarkMessageReceiver {

    private final BenchmarkJobService benchmarkJobService;

    public BenchmarkMessageReceiver(BenchmarkJobService benchmarkJobService) {
        this.benchmarkJobService = benchmarkJobService;
    }


}
