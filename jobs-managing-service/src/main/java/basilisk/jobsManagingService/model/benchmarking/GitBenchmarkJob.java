package basilisk.jobsManagingService.model.benchmarking;


import basilisk.jobsManagingService.model.repo.GitRepo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    private String commit_sha1;
}
