package basilisk.benchmarkService.repositories;


import basilisk.benchmarkService.domain.Iguana.IguanaConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IguanaConfigurationRepository extends CrudRepository<IguanaConfiguration, Long> {
}
