package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;

import basilisk.jobsManagingService.domain.BaseEntity;

/**
 * @author Fakhr Shaheen
 */
public class Connection extends BaseEntity {

    private String name;
    private String endpoint;
    private boolean requiresAuthentication;
    private String user;
    private String password;

    public Connection(String name, String endpoint, String user, String password) {
        this.name = name;
        this.endpoint = endpoint;
        this.user = user;
        this.password = password;
        this.requiresAuthentication=true;
    }

    public Connection(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
        this.requiresAuthentication=false;
    }
}
