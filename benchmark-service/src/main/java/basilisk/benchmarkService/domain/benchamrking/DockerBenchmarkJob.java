package basilisk.benchmarkService.domain.benchamrking;


import basilisk.benchmarkService.domain.DockerJobConfig;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Fakhr Shaheen
 */


@Setter
@SuperBuilder
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
