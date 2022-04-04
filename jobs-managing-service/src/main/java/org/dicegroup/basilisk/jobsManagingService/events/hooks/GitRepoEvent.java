package org.dicegroup.basilisk.jobsManagingService.events.hooks;


import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class GitRepoEvent implements Serializable {

    private RepoEventType eventType;
    private GitRepo repo;

}
