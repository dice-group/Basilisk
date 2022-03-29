package basilisk.jobsManagingService.repositories.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.DockerBenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DockerBenchmarkJobRepository extends CrudRepository<DockerBenchmarkJob, Long> {
}
