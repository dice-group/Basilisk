package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.docker.DockerImage;
import basilisk.hooksCheckingService.domain.docker.DockerTag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */
public interface DockerTagRepository extends CrudRepository<DockerTag,Long> {

    public Optional<DockerTag> findDockerTagByName(String name);
}
