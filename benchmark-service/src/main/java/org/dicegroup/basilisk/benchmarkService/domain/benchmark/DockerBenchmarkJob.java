package org.dicegroup.basilisk.benchmarkService.domain.benchmark;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dicegroup.basilisk.benchmarkService.domain.repo.DockerRepo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
public class DockerBenchmarkJob extends BenchmarkJob {

    @ManyToOne
    private DockerRepo repo;
    private String tagName;
    private String imageDigest;

}
