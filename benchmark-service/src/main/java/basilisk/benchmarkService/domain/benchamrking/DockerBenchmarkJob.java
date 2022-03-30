package basilisk.benchmarkService.domain.benchamrking;


import basilisk.benchmarkService.domain.DockerJobConfig;
import lombok.Setter;


@Setter
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
