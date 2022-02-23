package basilisk.jobsManagingService.dto;

import basilisk.jobsManagingService.model.repo.GitRepoType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepoDto extends RepoDto {

    @JsonProperty("repo_type")
    @JsonView(Views.Api.class)
    private GitRepoType repoType;

    @JsonProperty("branch_name")
    @JsonView(Views.Api.class)
    private String branchName;

}
