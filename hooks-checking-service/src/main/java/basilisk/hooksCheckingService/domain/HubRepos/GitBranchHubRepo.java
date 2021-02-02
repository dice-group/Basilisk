package basilisk.hooksCheckingService.domain.HubRepos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class GitBranchHubRepo extends GitHubRepo {

    private String branchName;

    public GitBranchHubRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken, String branchName) {
        super(repoName, repoOwner, isPrivate, OAuthToken);
        this.branchName=branchName;
    }

    public GitBranchHubRepo() {

    }
}
