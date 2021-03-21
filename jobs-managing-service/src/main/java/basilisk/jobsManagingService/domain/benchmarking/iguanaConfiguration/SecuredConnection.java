package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;

/**
 * @author Fakhr Shaheen
 */
public class SecuredConnection extends Connection{

    private String user;
    private String password;


    public SecuredConnection(String name, String endpoint,String updateEndpoint ,String user, String password) {
        super(name,endpoint,updateEndpoint);
        this.user = user;
        this.password = password;
    }
}
