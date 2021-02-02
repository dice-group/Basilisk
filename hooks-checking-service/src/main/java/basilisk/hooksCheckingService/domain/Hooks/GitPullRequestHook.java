package basilisk.hooksCheckingService.domain.Hooks;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class GitPullRequestHook extends GitHook{

    private String pullRequestState;
}
