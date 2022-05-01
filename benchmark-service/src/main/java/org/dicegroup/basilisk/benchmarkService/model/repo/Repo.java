package org.dicegroup.basilisk.benchmarkService.model.repo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;
import org.dicegroup.basilisk.benchmarkService.model.TripleStore;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance
public abstract class Repo {

    @Id
    private Long id;
    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String oAuthToken;

    @ManyToOne(cascade = CascadeType.ALL)
    private TripleStore tripleStore;

}
