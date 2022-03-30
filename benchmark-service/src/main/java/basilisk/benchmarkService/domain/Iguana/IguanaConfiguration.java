package basilisk.benchmarkService.domain.Iguana;


import basilisk.benchmarkService.domain.BaseEntity;
import basilisk.benchmarkService.domain.Iguana.storage.Storage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
public class IguanaConfiguration extends BaseEntity {

    @ManyToOne
    private IguanaConnection iguanaConnection;
    @ManyToOne
    private Dataset dataset;
    @ManyToOne
    private Storage storage;

//    private List<IguanaTask> iguanaTasks;
//    private List<String> iguanaMetrics;

}
