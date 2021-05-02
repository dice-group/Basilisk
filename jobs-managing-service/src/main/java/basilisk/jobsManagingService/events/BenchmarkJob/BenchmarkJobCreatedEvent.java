package basilisk.jobsManagingService.events.BenchmarkJob;

import basilisk.jobsManagingService.domain.benchmarking.BenchmarkJob;
import lombok.Builder;

import java.time.LocalDate;

/**
 * @author Fakhr Shaheen
 */

@Builder
public class BenchmarkJobCreatedEvent {

    private BenchmarkJob benchmarkJob;
    private LocalDate createdDate;
}
