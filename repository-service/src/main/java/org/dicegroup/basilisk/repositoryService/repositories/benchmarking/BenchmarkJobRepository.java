package org.dicegroup.basilisk.repositoryService.repositories.benchmarking;

import org.dicegroup.basilisk.dto.benchmark.JobStatus;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.BenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenchmarkJobRepository extends CrudRepository<BenchmarkJob, Long> {
    List<BenchmarkJob> findAllByStatus(JobStatus status);
}
