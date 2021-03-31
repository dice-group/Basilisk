package basilisk.benchmarkService.services;



import basilisk.benchmarkService.domain.Iguana.Dataset;
import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import basilisk.benchmarkService.domain.Iguana.IguanaConnection;
import basilisk.benchmarkService.domain.Iguana.storage.Storage;

import java.util.List;

/**
 * @author Fakhr Shaheen
 */
public interface IguanaConfigurationService {


    public IguanaConfiguration createDefaultIguanaConfiguration(IguanaConnection connection, List<Dataset> datasets, List<Storage> storages);

}
