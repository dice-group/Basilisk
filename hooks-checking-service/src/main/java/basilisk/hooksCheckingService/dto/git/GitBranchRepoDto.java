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
public class GitBranchRepoDto extends GitRepoDto {

    @JsonProperty("branch_name")
    private String branchName;
}
