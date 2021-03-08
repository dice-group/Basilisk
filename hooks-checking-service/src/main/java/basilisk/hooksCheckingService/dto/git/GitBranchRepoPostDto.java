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
public class GitBranchRepoPostDto extends GitRepoPostDto {

    @JsonProperty("branch_name")
    private String branchName;
}
