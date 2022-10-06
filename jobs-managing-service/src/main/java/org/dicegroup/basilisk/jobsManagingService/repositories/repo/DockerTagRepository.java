package org.dicegroup.basilisk.jobsManagingService.repositories.repo;


import org.dicegroup.basilisk.jobsManagingService.model.repo.docker.DockerRepo;
import org.dicegroup.basilisk.jobsManagingService.model.repo.docker.DockerTag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DockerTagRepository extends CrudRepository<DockerTag, Long> {

    Optional<DockerTag> findDockerTagByDockerRepoAndName(DockerRepo repo, String name);

}
