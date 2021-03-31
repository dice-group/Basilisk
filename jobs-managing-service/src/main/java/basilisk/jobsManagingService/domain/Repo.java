package basilisk.jobsManagingService.domain;

/**
 * @author Fakhr Shaheen
 */
public class Repo extends BaseEntity{

    private String repoName;

    private String repoOwner;

    private boolean isPrivate;

    private String OAuthToken;
}
