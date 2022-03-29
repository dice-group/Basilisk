package basilisk.jobsManagingService.events.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class GitBenchmarkJobCreatedEvent implements Serializable {

    private GitBenchmarkJob benchmarkJob;

}
