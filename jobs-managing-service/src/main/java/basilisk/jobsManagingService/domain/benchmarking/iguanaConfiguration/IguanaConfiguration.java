package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.storage.Storage;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.IguanaTask;
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
