package org.dicegroup.basilisk.benchmarkService.domain.benchmark;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dicegroup.basilisk.benchmarkService.domain.BaseEntity;
import org.dicegroup.basilisk.benchmarkService.domain.repo.Repo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@NoArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
public abstract class BenchmarkJob extends BaseEntity {

    @ManyToOne
    private Benchmark benchmark;

    private JobStatus status;

    @ManyToOne
    private Repo repo;

}
