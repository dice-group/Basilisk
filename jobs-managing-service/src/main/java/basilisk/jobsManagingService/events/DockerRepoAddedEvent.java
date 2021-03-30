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
public class DockerRepoAddedEvent  implements Serializable {
    private Long id;
    private String repoName;
    private String ownerName;
    private boolean isPrivate;
    private String OAuthToken;
}
