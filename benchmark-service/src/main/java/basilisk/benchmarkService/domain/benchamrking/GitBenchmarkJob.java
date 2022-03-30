package basilisk.benchmarkService.domain.benchamrking;


import basilisk.benchmarkService.domain.GitJobConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class GitBenchmarkJob extends BenchmarkJob {

    private GitJobConfig gitJobConfig;
}
