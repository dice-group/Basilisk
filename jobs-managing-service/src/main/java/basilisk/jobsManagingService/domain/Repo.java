package basilisk.jobsManagingService.domain;

import javax.persistence.Entity;

/**
 * @author Fakhr Shaheen
 */
@Entity
public class Repo extends BaseEntity{

    private String repoName;

    private String repoOwner;

    private boolean isPrivate;

    private String OAuthToken;
}
