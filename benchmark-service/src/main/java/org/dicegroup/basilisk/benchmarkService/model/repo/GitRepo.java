package org.dicegroup.basilisk.benchmarkService.model.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.repo.GitRepoType;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepo extends Repo {

    private GitRepoType gitRepoType;
    private String branchName;

}
