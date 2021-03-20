package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.docker.DockerImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */
public interface DockerImageRepository extends CrudRepository<DockerImage,Long> {
    
    Optional<DockerImage> findByDigest(String digest);
}