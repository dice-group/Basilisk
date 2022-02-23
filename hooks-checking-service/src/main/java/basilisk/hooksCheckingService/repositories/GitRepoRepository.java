package basilisk.hooksCheckingService.repositories;


import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.model.git.GitRepoType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo,Long> {

    Iterable<GitRepo> findAllByRepoType(GitRepoType type);

}
