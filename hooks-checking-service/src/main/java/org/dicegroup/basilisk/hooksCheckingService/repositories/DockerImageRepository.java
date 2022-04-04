package org.dicegroup.basilisk.hooksCheckingService.repositories;

import org.dicegroup.basilisk.hooksCheckingService.model.docker.DockerImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DockerImageRepository extends CrudRepository<DockerImage,Long> {
    
    Optional<DockerImage> findByDigest(String digest);
}