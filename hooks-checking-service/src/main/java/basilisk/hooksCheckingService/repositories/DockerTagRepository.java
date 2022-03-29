package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.model.docker.DockerRepo;
import basilisk.hooksCheckingService.model.docker.DockerTag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DockerTagRepository extends CrudRepository<DockerTag, Long> {

    Optional<DockerTag> findDockerTagByDockerRepoAndName(DockerRepo repo, String name);

}
