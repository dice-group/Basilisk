package org.dicegroup.basilisk.hooksCheckingService.repositories;


import org.dicegroup.basilisk.hooksCheckingService.model.git.GitHook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface GitHookRepository extends CrudRepository<GitHook,Long> {

    Optional<GitHook> findByCommitSha1(String sha1);

}
