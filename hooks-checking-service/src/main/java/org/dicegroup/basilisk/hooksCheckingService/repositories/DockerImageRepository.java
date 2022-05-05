package org.dicegroup.basilisk.hooksCheckingService.repositories;

import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerImage;
import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DockerImageRepository extends CrudRepository<DockerImage,Long> {
    Optional<DockerImage> findByDockerRepoAndDigest(DockerRepo repo, String digest);
}