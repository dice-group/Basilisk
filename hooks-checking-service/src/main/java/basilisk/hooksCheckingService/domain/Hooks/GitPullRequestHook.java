package basilisk.hooksCheckingService.domain.Hooks;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitPullRequestHook extends GitHook{

    String pullRequestState;
}
