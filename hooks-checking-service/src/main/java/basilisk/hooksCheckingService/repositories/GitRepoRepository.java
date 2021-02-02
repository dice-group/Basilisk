package basilisk.hooksCheckingService.repositories;


import basilisk.hooksCheckingService.domain.git.GitRepo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */


public interface GitRepoRepository extends CrudRepository<GitRepo,Long> {

}
