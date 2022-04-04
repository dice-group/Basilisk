package org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.BenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenchmarkJobRepository extends CrudRepository<BenchmarkJob, Long> {
}
