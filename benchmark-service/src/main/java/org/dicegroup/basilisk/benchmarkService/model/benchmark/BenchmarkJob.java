package org.dicegroup.basilisk.benchmarkService.model.benchmark;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;
import org.dicegroup.basilisk.benchmarkService.model.repo.Repo;
import org.dicegroup.basilisk.dto.benchmark.JobStatus;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
public abstract class BenchmarkJob {

    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Benchmark benchmark;

    private JobStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Repo repo;

}
