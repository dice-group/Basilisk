package org.dicegroup.basilisk.benchmarkService.model.iguana;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Connection {

    private String name;
    private String endpoint;
    private String updateEndpoint;

    private String user;
    private String password;

    private String version;

}
