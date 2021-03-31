package basilisk.benchmarkService.domain.Iguana;


import basilisk.benchmarkService.domain.BaseEntity;
import basilisk.benchmarkService.domain.Iguana.storage.Storage;
import basilisk.benchmarkService.domain.Iguana.task.IguanaTask;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
@Setter
@Getter
@Builder
public class IguanaConfiguration extends BaseEntity {

    private List<IguanaConnection> iguanaConnections;
    private List<Dataset> datasets;
    private List<Storage> storages;
    private List<IguanaTask> iguanaTasks;
    private List<String> iguanaMetrics;
}
