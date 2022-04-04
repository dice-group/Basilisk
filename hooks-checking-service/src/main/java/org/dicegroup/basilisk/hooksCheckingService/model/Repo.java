package org.dicegroup.basilisk.hooksCheckingService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;


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

}
