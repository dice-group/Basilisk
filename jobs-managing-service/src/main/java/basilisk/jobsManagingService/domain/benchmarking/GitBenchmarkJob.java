package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.GitJobConfig;
import lombok.*;

/**
 * @author Fakhr Shaheen
 */


@Builder
@Setter
@Getter
public class GitBenchmarkJob extends BenchmarkJob{

    private GitJobConfig gitJobConfig;
}
