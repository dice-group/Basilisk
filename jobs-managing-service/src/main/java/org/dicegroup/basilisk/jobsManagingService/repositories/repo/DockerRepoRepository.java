package org.dicegroup.basilisk.jobsManagingService.repositories.repo;

import org.dicegroup.basilisk.jobsManagingService.model.repo.DockerRepo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DockerRepoRepository extends CrudRepository<DockerRepo, Long> {
}