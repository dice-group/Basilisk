package org.dicegroup.basilisk.benchmarkService.domain.benchamrking;


import org.dicegroup.basilisk.benchmarkService.domain.DockerJobConfig;
import lombok.Setter;


@Setter
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
