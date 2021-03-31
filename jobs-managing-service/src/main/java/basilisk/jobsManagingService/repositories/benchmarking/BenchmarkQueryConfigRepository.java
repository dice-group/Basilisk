package basilisk.jobsManagingService.repositories.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.QueryConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fakhr Shaheen
 */
@Repository
public interface BenchmarkQueryConfigRepository extends CrudRepository<QueryConfig,Long> {
}
