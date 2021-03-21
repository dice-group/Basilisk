package basilisk.jobsManagingService.repositories.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.IguanaConfiguration;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fakhr Shaheen
 */
public interface IguanaConfigurationRepository extends CrudRepository<IguanaConfiguration,Long> {
}
