package org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.DockerBenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DockerBenchmarkJobRepository extends CrudRepository<DockerBenchmarkJob, Long> {
}
