package basilisk.jobsManagingService.repositories.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitBenchmarkJobRepository extends CrudRepository<GitBenchmarkJob, Long> {
}
