package org.dicegroup.basilisk.repositoryService.repositories.benchmarking;

import org.dicegroup.basilisk.repositoryService.model.benchmarking.GitBenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitBenchmarkJobRepository extends CrudRepository<GitBenchmarkJob, Long> {
}
