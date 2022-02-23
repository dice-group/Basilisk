package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.model.docker.DockerRepo;
import org.springframework.data.repository.CrudRepository;


public interface DockerRepoRepository extends CrudRepository<DockerRepo,Long> {
}
