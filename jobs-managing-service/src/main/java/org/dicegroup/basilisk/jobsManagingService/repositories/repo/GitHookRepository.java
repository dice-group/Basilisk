package org.dicegroup.basilisk.jobsManagingService.repositories.repo;

import org.dicegroup.basilisk.jobsManagingService.model.repo.git.GitHook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface GitHookRepository extends CrudRepository<GitHook, Long> {

    Optional<GitHook> findByCommitSha1(String sha1);

}
