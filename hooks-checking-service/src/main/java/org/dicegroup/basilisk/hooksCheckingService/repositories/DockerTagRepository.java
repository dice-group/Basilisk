package org.dicegroup.basilisk.hooksCheckingService.repositories;

import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerTag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DockerTagRepository extends CrudRepository<DockerTag, Long> {

    Optional<DockerTag> findDockerTagByDockerRepoAndName(DockerRepo repo, String name);

}
