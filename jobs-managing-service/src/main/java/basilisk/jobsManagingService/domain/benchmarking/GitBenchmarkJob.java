package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.GitJobConfig;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * @author Fakhr Shaheen
 */



@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class GitBenchmarkJob extends BenchmarkJob{

    private GitJobConfig gitJobConfig;
}
