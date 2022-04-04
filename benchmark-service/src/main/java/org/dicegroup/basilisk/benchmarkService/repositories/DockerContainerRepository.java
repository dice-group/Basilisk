package org.dicegroup.basilisk.benchmarkService.repositories;

import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DockerContainerRepository extends CrudRepository<DockerContainer, Long> {
    Optional<DockerContainer> findByImageName(String imageName);
}
