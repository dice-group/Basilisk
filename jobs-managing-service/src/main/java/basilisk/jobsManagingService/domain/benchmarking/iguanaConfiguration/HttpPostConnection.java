package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;

/**
 * @author Fakhr Shaheen
 */
public class HttpPostConnection extends Connection {

    private String updateEndpoint;

    public HttpPostConnection(String name, String endpoint,String updateEndpoint, String user, String password) {
        super(name, endpoint, user, password);
        this.updateEndpoint=updateEndpoint;
    }

    public HttpPostConnection(String name, String endpoint,String updateEndpoint) {
        super(name, endpoint);
        this.updateEndpoint=updateEndpoint;
    }
}
