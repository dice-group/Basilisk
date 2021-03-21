package basilisk.jobsManagingService.domain.benchmarking.iguanaConfiguration;

import basilisk.jobsManagingService.domain.BaseEntity;

/**
 * @author Fakhr Shaheen
 */
public class Connection extends BaseEntity {

    private String name;
    private String endpoint;
    private String updateEndpoint;



    public Connection(String name, String endpoint,String updateEndpoint) {
        this.name = name;
        this.endpoint = endpoint;
        this.updateEndpoint=updateEndpoint;
    }
}
