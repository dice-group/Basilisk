package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.Dataset;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.IguanaConfiguration;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.IguanaConnection;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.storage.Storage;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public interface IguanaConfigurationService {


    public IguanaConfiguration createDefaultIguanaConfiguration(IguanaConnection connection, List<Dataset> datasets, List<Storage> storages);

}
