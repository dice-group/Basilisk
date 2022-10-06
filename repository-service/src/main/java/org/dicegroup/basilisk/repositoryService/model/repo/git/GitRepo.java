package org.dicegroup.basilisk.repositoryService.model.repo.git;

import lombok.Getter;
import lombok.Setter;
import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.repositoryService.model.repo.Repo;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class GitRepo extends Repo {

    private GitRepoType gitRepoType;
    private String branchName;

}
