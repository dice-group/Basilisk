package org.dicegroup.basilisk.hooksCheckingService.repositories;


import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepoType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo,Long> {

    Iterable<GitRepo> findAllByRepoType(GitRepoType type);

}
