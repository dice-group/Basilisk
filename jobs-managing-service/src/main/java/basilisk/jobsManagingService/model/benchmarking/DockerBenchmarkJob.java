package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.DockerJobConfig;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;

}
