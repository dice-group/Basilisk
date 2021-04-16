package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.GitJobConfig;
import lombok.*;

/**
 * @author Fakhr Shaheen
 */



@Setter
@Getter
public class GitBenchmarkJob extends BenchmarkJob{

    private GitJobConfig gitJobConfig;
}
