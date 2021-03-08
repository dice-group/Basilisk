package basilisk.hooksCheckingService.dto.git;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GitRepoPostDto {

    @JsonProperty("repo_name")
    private String repoName;
    @JsonProperty("repo_owner")
    private String repoOwner;
    @JsonProperty("is_private")
    private boolean isPrivate;
    @JsonProperty("oAuth_token")
    private String OAuthToken;

}
