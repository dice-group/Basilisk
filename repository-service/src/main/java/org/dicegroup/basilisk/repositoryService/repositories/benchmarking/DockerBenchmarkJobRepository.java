package org.dicegroup.basilisk.repositoryService.repositories.benchmarking;

import org.dicegroup.basilisk.repositoryService.model.benchmarking.DockerBenchmarkJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DockerBenchmarkJobRepository extends CrudRepository<DockerBenchmarkJob, Long> {
}
