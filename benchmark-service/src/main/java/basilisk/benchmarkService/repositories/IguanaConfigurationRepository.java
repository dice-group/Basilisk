package basilisk.benchmarkService.repositories;


import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fakhr Shaheen
 */
public interface IguanaConfigurationRepository extends CrudRepository<IguanaConfiguration,Long> {
}
