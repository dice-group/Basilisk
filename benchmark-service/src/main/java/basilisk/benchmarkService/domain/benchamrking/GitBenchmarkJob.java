package basilisk.benchmarkService.domain.benchamrking;


import basilisk.benchmarkService.domain.GitJobConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Fakhr Shaheen
 */



@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class GitBenchmarkJob extends BenchmarkJob {

    private GitJobConfig gitJobConfig;
}
