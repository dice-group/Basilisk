package org.dicegroup.basilisk.jobsManagingService.repositories.repo;

import org.dicegroup.basilisk.jobsManagingService.model.repo.docker.DockerImage;
import org.dicegroup.basilisk.jobsManagingService.model.repo.docker.DockerRepo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DockerImageRepository extends CrudRepository<DockerImage,Long> {
    Optional<DockerImage> findByDockerRepoAndDigest(DockerRepo repo, String digest);
}