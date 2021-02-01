package basilisk.hooksCheckingService.domain.HooksRepos;


import lombok.Getter;

@Getter
public class GitRepo extends Repo {

    String repoName;
    String repoOwner;
    boolean isPrivate;
    String OAuthToken;

}
