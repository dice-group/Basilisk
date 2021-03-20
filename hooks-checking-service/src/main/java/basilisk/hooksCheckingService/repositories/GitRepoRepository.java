package basilisk.hooksCheckingService.repositories;


import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Fakhr Shaheen
 */


@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo,Long> {

    Iterable<GitRepo> findAllByType(GitType type);


}
