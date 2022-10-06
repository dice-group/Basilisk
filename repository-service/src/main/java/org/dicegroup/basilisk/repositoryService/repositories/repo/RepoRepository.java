package org.dicegroup.basilisk.repositoryService.repositories.repo;

import org.dicegroup.basilisk.repositoryService.model.repo.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRepository extends CrudRepository<Repo, Long> {
}
