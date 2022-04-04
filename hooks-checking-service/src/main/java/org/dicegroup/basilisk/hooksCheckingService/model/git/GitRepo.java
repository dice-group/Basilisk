package org.dicegroup.basilisk.hooksCheckingService.model.git;


import org.dicegroup.basilisk.hooksCheckingService.model.Repo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
