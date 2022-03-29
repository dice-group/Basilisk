package basilisk.jobsManagingService.repositories.repo;

import basilisk.jobsManagingService.model.repo.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRepository extends CrudRepository<Repo, Long> {
}
