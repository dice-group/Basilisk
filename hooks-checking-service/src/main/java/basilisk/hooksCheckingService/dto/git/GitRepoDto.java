package basilisk.hooksCheckingService.dto.git;

import basilisk.hooksCheckingService.core.Views;
import basilisk.hooksCheckingService.domain.git.GitType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

/**
 * @author Fakhr Shaheen, Fabian Rensing
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GitRepoDto {

    @JsonProperty("repo_id")
    @JsonView(Views.Api.class)
    private Long id;

    @JsonProperty("repo_name")
    @JsonView(Views.Api.class)
    private String repoName;

    @JsonProperty("repo_owner")
    @JsonView(Views.Api.class)
    private String repoOwner;

    @JsonProperty("is_private")
    @JsonView(Views.Api.class)
    private boolean isPrivate;

    @JsonProperty("oAuth_token")
    @JsonView(Views.Admin.class)
    private String oAuthToken;

    @JsonProperty("hook_type")
    @JsonView(Views.Api.class)
    private GitType type;

}
