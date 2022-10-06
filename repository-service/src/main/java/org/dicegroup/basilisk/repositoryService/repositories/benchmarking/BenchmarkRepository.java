package org.dicegroup.basilisk.repositoryService.repositories.benchmarking;

import org.dicegroup.basilisk.repositoryService.model.benchmarking.Benchmark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BenchmarkRepository extends CrudRepository<Benchmark,Long> {

}
