package basilisk.jobsManagingService.domain;

/**
 * @author Fakhr Shaheen
 */
public class TripleStore extends BaseEntity{

    private String name;
    private String endpoint;
    private String updateEndpoint;
    private boolean requiresAuthentication;
    private String username;
    private String password;
    
    private Repo dockerRepo;
    private Repo gitRepo;
}
