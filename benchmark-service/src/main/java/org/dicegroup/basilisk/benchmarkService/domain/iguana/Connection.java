package org.dicegroup.basilisk.benchmarkService.domain.iguana;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Connection {

    private String name;
    private String endpoint;
    private String updateEndpoint;

    private String user;
    private String password;

    private String version;

}
