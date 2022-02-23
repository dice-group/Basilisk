package basilisk.jobsManagingService.repositories.repo;

import basilisk.jobsManagingService.model.repo.GitRepo;
import basilisk.jobsManagingService.model.repo.GitRepoType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo, Long> {
    List<GitRepo> findAllByRepoType(GitRepoType type);
}
