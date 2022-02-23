package basilisk.jobsManagingService.events.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.BenchmarkJob;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;


@Builder
public class BenchmarkJobCreatedEvent implements Serializable {

    private BenchmarkJob benchmarkJob;
    private LocalDate createdDate;
}
