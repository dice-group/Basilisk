package org.dicegroup.basilisk.jobsManagingService.repositories.repo;

import org.dicegroup.basilisk.jobsManagingService.model.repo.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRepository extends CrudRepository<Repo, Long> {
}
