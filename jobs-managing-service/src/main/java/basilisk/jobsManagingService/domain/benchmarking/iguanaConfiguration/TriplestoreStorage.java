package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


/**
 * @author Fakhr Shaheen
 */
public class TriplestoreStorage extends Storage{

    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String user;
    private String password;

    @Autowired
    private Environment env;

    public TriplestoreStorage(String endpoint, String updateEndpoint, String user, String password) {
        this.className=env.getProperty("IguanaConfiguration.ClassName.TriplestoreStorage");
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;
        this.requiresAuthentication = true;
        this.user = user;
        this.password = password;
    }

    public TriplestoreStorage(String endpoint, String updateEndpoint) {
        this.endpoint = endpoint;
        this.updateEndpoint = updateEndpoint;
        this.requiresAuthentication = false;
    }
}
