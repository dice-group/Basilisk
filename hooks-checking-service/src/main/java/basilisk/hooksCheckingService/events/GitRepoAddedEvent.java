package basilisk.hooksCheckingService.events;

import basilisk.hooksCheckingService.domain.git.GitType;
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
public class GitRepoAddedEvent  implements Serializable {

    private Long id;
    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String OAuthToken;
    private GitType type;
}
