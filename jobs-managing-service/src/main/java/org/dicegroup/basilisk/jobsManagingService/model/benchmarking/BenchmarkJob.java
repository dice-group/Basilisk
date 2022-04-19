package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;
import org.dicegroup.basilisk.jobsManagingService.model.repo.Repo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;


@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance
public abstract class BenchmarkJob extends BaseEntity {

    @ManyToOne
    private Repo repo;

    @ManyToOne
    private Benchmark benchmark;

    private JobStatus status;

}
