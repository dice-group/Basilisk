package basilisk.benchmarkService.domain.Iguana.storage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;


/**
 * @author Fakhr Shaheen
 */
public class TriplestoreStorage extends Storage{

    private String endpoint;
    private String updateEndpoint;

    @Value( "${IguanaConfiguration.ClassName.TriplestoreStorage}" )
    private  String className;


    public TriplestoreStorage(String endpoint, String updateEndpoint) {
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;

    }
}
