package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.docker.DockerHook;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fakhr Shaheen
 */
public interface DockeHookRepository extends CrudRepository<DockerHook,Long> {
}
