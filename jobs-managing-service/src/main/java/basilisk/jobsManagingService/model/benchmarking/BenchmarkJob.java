package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.BaseEntity;
import basilisk.jobsManagingService.model.TripleStore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance
public abstract class BenchmarkJob extends BaseEntity {

    @ManyToOne
    private DataSetConfig dataSetConfig;
    @OneToMany
    private List<QueryConfig> queryConfigs;
    @ManyToOne
    private TripleStore tripleStore;
    private JobStatus status;

}