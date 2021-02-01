package basilisk.hooksCheckingService.domain.HooksRepos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GitBranchRepo extends GitRepo{

    String branchName;

    public GitBranchRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken,String branchName) {
        super(repoName, repoOwner, isPrivate, OAuthToken);
        this.branchName=branchName;
    }
}
