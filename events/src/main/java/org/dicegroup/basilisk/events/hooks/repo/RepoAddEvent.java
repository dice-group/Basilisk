package org.dicegroup.basilisk.events.hooks.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class RepoAddEvent implements Serializable {

    private Long id;
    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String oAuthToken;

}
