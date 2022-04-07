package org.dicegroup.basilisk.benchmarkService.domain.benchmark;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.domain.GitJobConfig;


@Setter
@Getter
@NoArgsConstructor
public class GitBenchmarkJob extends BenchmarkJob {

    private GitJobConfig gitJobConfig;
}
