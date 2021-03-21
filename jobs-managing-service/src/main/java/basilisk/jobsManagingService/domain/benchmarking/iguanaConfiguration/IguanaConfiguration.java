package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;

import basilisk.jobsManagingService.domain.BaseEntity;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.storage.Storage;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.task.Task;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public class IguanaConfiguration extends BaseEntity {

    private List<Connection> connections;
    private List<Task> tasks;
    private List<Dataset> datasets;
    private List<Storage> storages;
    private List<Metric> metrics;
}
