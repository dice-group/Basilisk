package basilisk.benchmarkService.events;


import basilisk.benchmarkService.domain.benchamrking.BenchmarkJob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Fakhr Shaheen
 */

@Builder
@Setter
@Getter
public class BenchmarkJobCreatedEvent {

    private BenchmarkJob benchmarkJob;
    private LocalDate createdDate;
}
