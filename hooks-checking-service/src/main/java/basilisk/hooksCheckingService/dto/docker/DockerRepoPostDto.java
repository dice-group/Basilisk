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
public class DockerRepoPostDto {

    @JsonProperty("repo_name")
    private String repoName;

    @JsonProperty("owner_name")
    private String ownerName;

    @JsonProperty("is_private")
    private boolean isPrivate;
}
