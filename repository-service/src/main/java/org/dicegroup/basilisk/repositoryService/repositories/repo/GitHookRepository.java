package org.dicegroup.basilisk.repositoryService.repositories.repo;

import org.dicegroup.basilisk.repositoryService.model.repo.git.GitHook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface GitHookRepository extends CrudRepository<GitHook, Long> {

    Optional<GitHook> findByCommitSha1(String sha1);

}
