package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.DockerJobConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * @author Fakhr Shaheen
 */


@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
public class DockerBenchmarkJob extends BenchmarkJob{

    private DockerJobConfig dockerJobConfig;
}
