package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.storage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


/**
 * @author Fakhr Shaheen
 */
public class TriplestoreStorage extends Storage{

    private String endpoint;
    private String updateEndpoint;


    @Autowired
    private Environment env;


    public TriplestoreStorage(String endpoint, String updateEndpoint) {
        this.className=env.getProperty("IguanaConfiguration.ClassName.TriplestoreStorage");
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;

    }
}
