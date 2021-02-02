package basilisk.hooksCheckingService.domain.HubRepos;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class GitHubRepo extends HubRepo {

    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String OAuthToken;

    public GitHubRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken) {
        this.repoName = repoName;
        this.repoOwner = repoOwner;
        this.isPrivate = isPrivate;
        this.OAuthToken = OAuthToken;
    }

    public GitHubRepo() {

    }
}
