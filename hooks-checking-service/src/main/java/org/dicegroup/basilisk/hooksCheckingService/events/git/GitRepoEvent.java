package org.dicegroup.basilisk.hooksCheckingService.events.git;

import org.dicegroup.basilisk.hooksCheckingService.events.RepoEventType;
import org.dicegroup.basilisk.hooksCheckingService.model.git.GitRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepoEvent implements Serializable {

    private RepoEventType eventType;
    private GitRepo repo;

}
