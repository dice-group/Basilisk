package org.dicegroup.basilisk.dto.repo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.Views;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepoDto extends RepoDto {

    @JsonProperty("git_repo_type")
    @JsonView(Views.Api.class)
    private GitRepoType gitRepoType;

    @JsonProperty("branch_name")
    @JsonView(Views.Api.class)
    private String branchName;

}
