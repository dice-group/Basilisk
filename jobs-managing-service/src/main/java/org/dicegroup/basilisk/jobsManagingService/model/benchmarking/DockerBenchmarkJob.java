package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.repo.DockerRepo;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
public class DockerBenchmarkJob extends BenchmarkJob {

    @ManyToOne
    private DockerRepo repo;

    private String tagName;

    private String imageDigest;

}
