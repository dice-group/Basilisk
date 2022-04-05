package org.dicegroup.basilisk.events.hooks.repo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepoAddEvent extends RepoAddEvent {

    private GitRepoType repoType;
    private String branchName;

}
