package org.dicegroup.basilisk.repositoryService.repositories.repo;

import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerRepo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DockerRepoRepository extends CrudRepository<DockerRepo, Long> {
}
