package org.dicegroup.basilisk.benchmarkService.domain.Iguana.storage;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class SecuredTripleStoreStorage extends TriplestoreStorage {

    private String user;
    private String password;

    public SecuredTripleStoreStorage(String endpoint, String updateEndpoint, String user, String password) {
        super(endpoint, updateEndpoint);
        this.user = user;
        this.password = password;
    }

}
