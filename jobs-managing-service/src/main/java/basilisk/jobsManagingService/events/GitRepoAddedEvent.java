package basilisk.jobsManagingService.events;


import lombok.*;

import java.io.Serializable;



/**
 * @author Fakhr Shaheen
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitRepoAddedEvent implements Serializable {
    public enum GitType {
        release,pull_request,branch

    }
    private Long id;
    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String OAuthToken;
    private GitType type;
}
