package basilisk.jobsManagingService.domain.benchmarking;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.TripleStore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */

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
