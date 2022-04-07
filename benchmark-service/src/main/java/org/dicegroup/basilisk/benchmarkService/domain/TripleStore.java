package org.dicegroup.basilisk.benchmarkService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripleStore extends BaseEntity{

    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;

}
