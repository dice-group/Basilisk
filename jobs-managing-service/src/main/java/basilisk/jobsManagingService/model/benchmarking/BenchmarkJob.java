package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.BaseEntity;
import lombok.*;
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

    @ManyToOne
    private TripleStore tripleStore;

    private JobStatus status;

}
