package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.DockerJobConfig;
import lombok.*;

/**
 * @author Fakhr Shaheen
 */


@Setter
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
