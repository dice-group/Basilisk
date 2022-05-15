package org.dicegroup.basilisk.benchmarkService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripleStore {

    @Id
    private Long id;
    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;

    private int exposedPort;

    private String dataSetPath;

    private String entryPoint;

    private String preScriptHook;

}