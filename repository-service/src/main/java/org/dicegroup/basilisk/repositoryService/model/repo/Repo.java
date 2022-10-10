package org.dicegroup.basilisk.repositoryService.model.repo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.repositoryService.model.BaseEntity;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.TripleStore;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance
public abstract class Repo extends BaseEntity {

    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String oAuthToken;

    @ManyToOne
    private TripleStore tripleStore;

}
