package org.dicegroup.basilisk.jobsManagingService.model.repo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class GitRepo extends Repo {

    private GitRepoType gitRepoType;
    private String branchName;

}
