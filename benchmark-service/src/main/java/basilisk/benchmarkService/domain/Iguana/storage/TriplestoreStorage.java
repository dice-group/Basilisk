package basilisk.benchmarkService.domain.Iguana.storage;


import org.springframework.beans.factory.annotation.Value;


/**
 * @author Fakhr Shaheen
 */

public class TriplestoreStorage extends Storage{

    private String endpoint;
    private String updateEndpoint;

    public TriplestoreStorage(String endpoint, String updateEndpoint) {
        super( "NTFileStorage");
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;

    }
}
