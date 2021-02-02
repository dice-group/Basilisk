package basilisk.hooksCheckingService.domain.git;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@Setter
@Entity
public class GitBranchRepo extends GitRepo {

    @Column(name = "branch")
    private String branchName;

    public GitBranchRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken, String branchName) {
        super(repoName, repoOwner, isPrivate, OAuthToken);
        this.branchName=branchName;
    }

    public GitBranchRepo() {

    }
}
