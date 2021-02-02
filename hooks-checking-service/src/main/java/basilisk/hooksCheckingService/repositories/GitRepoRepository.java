package basilisk.hooksCheckingService.repositories;

import basilisk.hooksCheckingService.domain.git.GitRepo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Fakhr Shaheen
 */


public interface GitRepoRepository extends CrudRepository<GitRepo,Long> {
}
