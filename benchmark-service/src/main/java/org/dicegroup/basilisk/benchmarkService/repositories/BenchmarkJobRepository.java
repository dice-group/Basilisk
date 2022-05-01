package org.dicegroup.basilisk.benchmarkService.repositories;

import org.dicegroup.basilisk.benchmarkService.model.benchmark.BenchmarkJob;
import org.dicegroup.basilisk.dto.benchmark.JobStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenchmarkJobRepository extends CrudRepository<BenchmarkJob, Long> {
    List<BenchmarkJob> findAllByStatus(JobStatus status);
}
