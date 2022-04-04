package org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.Benchmark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BenchmarkRepository extends CrudRepository<Benchmark,Long> {

}
