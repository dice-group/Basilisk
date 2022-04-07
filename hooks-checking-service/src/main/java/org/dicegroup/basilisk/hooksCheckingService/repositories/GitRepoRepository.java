package org.dicegroup.basilisk.hooksCheckingService.repositories;


import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GitRepoRepository extends CrudRepository<GitRepo,Long> {

    Iterable<GitRepo> findAllByRepoType(GitRepoType type);

}
