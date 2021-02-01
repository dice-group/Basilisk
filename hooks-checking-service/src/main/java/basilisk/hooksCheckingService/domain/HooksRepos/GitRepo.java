package basilisk.hooksCheckingService.domain.HooksRepos;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GitRepo extends Repo {
    public GitRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken) {
        this.repoName = repoName;
        this.repoOwner = repoOwner;
        this.isPrivate = isPrivate;
        this.OAuthToken = OAuthToken;
    }

    String repoName;
    String repoOwner;
    boolean isPrivate;
    String OAuthToken;

}
