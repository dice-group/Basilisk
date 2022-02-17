package basilisk.hooksCheckingService.dto.docker;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DockerRepoDto {

    @JsonProperty("repo_id")
    private Long id;

    @JsonProperty("repo_name")
    private String repoName;

    @JsonProperty("repo_owner")
    private String repoOwner;

    @JsonProperty("is_private")
    private boolean isPrivate;
}
