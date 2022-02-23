package basilisk.jobsManagingService.repositories.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.QueryConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BenchmarkQueryConfigRepository extends CrudRepository<QueryConfig,Long> {

    Optional<QueryConfig> findByName(String QueryConfigName );

    List<QueryConfig> findAllByActive(boolean isActive);
}
