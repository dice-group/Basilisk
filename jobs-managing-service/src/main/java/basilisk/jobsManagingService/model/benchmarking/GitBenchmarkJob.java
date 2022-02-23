package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.GitJobConfig;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class GitBenchmarkJob extends BenchmarkJob{

    private GitJobConfig gitJobConfig;
}
