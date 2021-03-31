package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.DockerJobConfig;
import lombok.*;

/**
 * @author Fakhr Shaheen
 */


@Builder
@Setter
@Getter
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
