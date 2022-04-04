package org.dicegroup.basilisk.benchmarkService.domain;

import lombok.Getter;


@Getter
public class TripleStore extends BaseEntity{

    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;

}
