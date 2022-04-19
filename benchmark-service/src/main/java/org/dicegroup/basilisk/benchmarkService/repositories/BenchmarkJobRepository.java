package org.dicegroup.basilisk.benchmarkService.repositories;

import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenchmarkJobRepository extends CrudRepository<BenchmarkJob, Long> {
}
