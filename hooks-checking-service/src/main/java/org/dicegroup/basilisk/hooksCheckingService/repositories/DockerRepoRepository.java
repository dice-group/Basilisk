package org.dicegroup.basilisk.hooksCheckingService.repositories;

import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.springframework.data.repository.CrudRepository;


public interface DockerRepoRepository extends CrudRepository<DockerRepo,Long> {
}
