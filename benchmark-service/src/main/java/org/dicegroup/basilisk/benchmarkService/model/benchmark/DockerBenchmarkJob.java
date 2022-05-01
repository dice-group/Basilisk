package org.dicegroup.basilisk.benchmarkService.model.benchmark;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dicegroup.basilisk.benchmarkService.model.repo.DockerRepo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
public class DockerBenchmarkJob extends BenchmarkJob {

    @ManyToOne(cascade = CascadeType.ALL)
    private DockerRepo repo;
    private String tagName;
    private String imageDigest;

}
