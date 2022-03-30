package basilisk.benchmarkService.domain.Iguana.storage;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
public class TriplestoreStorage extends Storage{

    private String endpoint;
    private String updateEndpoint;

    public TriplestoreStorage(String endpoint, String updateEndpoint) {
        super( "NTFileStorage");
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;

    }
}
