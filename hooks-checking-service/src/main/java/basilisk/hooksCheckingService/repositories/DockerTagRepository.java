package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.docker.DockerTag;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fakhr Shaheen
 */
public interface DockerTagRepository extends CrudRepository<DockerTag,Long> {
}
