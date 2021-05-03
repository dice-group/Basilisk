package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.DockerJobConfig;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Fakhr Shaheen
 */


@Setter
@SuperBuilder
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
