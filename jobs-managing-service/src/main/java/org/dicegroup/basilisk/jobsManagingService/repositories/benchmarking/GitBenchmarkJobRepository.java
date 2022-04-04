package org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitBenchmarkJobRepository extends CrudRepository<GitBenchmarkJob, Long> {
}
