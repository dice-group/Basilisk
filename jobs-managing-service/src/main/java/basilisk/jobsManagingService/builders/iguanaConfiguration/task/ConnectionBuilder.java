package basilisk.jobsManagingService.builders.iguanaConfiguration.task;

import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.Connection;
import basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration.SecuredConnection;

/**
 * @author Fakhr Shaheen
 */
public class ConnectionBuilder {

    private String name;
    private String endpoint;
    private String updateEndpoint;
    private String user;
    private String password;

    public ConnectionBuilder(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    public ConnectionBuilder setUpdateEndpoint( String updateEndpoint)
    {
        this.updateEndpoint=updateEndpoint;
        return this;
    }

    public ConnectionBuilder setUser( String user)
    {
        this.user=user;
        return this;
    }

    public ConnectionBuilder setPassword( String password)
    {
        this.password=password;
        return this;
    }

    public Connection build()
    {
        if(user!=null)
            return new SecuredConnection(name,endpoint,updateEndpoint,user,password);
        else
            return new Connection(name,endpoint,updateEndpoint);

    }


}
