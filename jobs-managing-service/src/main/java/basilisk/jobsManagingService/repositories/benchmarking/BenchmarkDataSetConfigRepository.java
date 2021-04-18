package basilisk.jobsManagingService.repositories.benchmarking;


import basilisk.jobsManagingService.domain.benchmarking.DataSetConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */
@Repository
public interface BenchmarkDataSetConfigRepository extends CrudRepository<DataSetConfig,Long> {

    Optional<DataSetConfig> findByName(String name);

    List<DataSetConfig> findAllByActive(boolean isActive);
}
