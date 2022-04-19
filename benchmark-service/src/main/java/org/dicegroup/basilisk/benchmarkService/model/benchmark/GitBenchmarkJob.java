package org.dicegroup.basilisk.benchmarkService.model.benchmark;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.model.GitJobConfig;


@Setter
@Getter
@NoArgsConstructor
public class GitBenchmarkJob extends BenchmarkJob {

    private GitJobConfig gitJobConfig;
}
