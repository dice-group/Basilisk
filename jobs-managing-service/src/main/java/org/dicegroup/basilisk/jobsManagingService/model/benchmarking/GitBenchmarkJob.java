package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class GitBenchmarkJob extends BenchmarkJob {

    @ManyToOne
    private GitRepo repo;

    private String branchName;
    private String url;
    private String commitSha1;
}
