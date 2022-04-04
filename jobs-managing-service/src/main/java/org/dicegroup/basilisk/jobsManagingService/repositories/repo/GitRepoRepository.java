package org.dicegroup.basilisk.jobsManagingService.repositories.repo;

import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepo;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepoType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo, Long> {
    List<GitRepo> findAllByGitRepoType(GitRepoType type);
}
