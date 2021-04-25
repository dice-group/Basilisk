package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.GitJobConfig;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Fakhr Shaheen
 */



@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class GitBenchmarkJob extends BenchmarkJob{

    private GitJobConfig gitJobConfig;
}
