package org.dicegroup.basilisk.hooksCheckingService.model.git;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.hooksCheckingService.model.Repo;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepo extends Repo {

    private GitRepoType repoType;
    private String branchName;

}
