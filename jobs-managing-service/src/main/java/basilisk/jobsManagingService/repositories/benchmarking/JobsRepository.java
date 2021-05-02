package basilisk.jobsManagingService.repositories.benchmarking;

import basilisk.jobsManagingService.domain.benchmarking.BenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fakhr Shaheen
 */

@Repository
public interface JobsRepository extends CrudRepository<BenchmarkJob,Long> {
}
