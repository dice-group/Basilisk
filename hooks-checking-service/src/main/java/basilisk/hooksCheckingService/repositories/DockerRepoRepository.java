package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fakhr Shaheen
 */
public interface DockerRepoRepository extends CrudRepository<DockerRepo,Long> {
}
