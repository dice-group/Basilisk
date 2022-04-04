package org.dicegroup.basilisk.jobsManagingService.model.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    private Benchmark benchmark;

    private JobStatus status;

}
