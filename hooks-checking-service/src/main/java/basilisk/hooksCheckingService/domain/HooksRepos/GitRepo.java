package basilisk.hooksCheckingService.domain.HooksRepos;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class GitRepo extends Repo {
    public GitRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken) {
        this.repoName = repoName;
        this.repoOwner = repoOwner;
        this.isPrivate = isPrivate;
        this.OAuthToken = OAuthToken;
    }

    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String OAuthToken;

    public GitRepo() {

    }
}
