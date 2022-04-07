package org.dicegroup.basilisk.benchmarkService.repositories;

import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.ContainerStatus;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.DockerContainer;
import org.dicegroup.basilisk.benchmarkService.domain.dockerContainer.ImageStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DockerContainerRepository extends CrudRepository<DockerContainer, Long> {
    Optional<DockerContainer> findByImageName(String imageName);

    List<DockerContainer> findAllByImageStatusAndContainerStatus(ImageStatus imageStatus, ContainerStatus containerStatus);
}
