package org.dicegroup.basilisk.jobsManagingService.dto.repo;

import org.dicegroup.basilisk.jobsManagingService.dto.Views;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepoType;
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

    @JsonProperty("git_repo_type")
    @JsonView(Views.Api.class)
    private GitRepoType gitRepoType;

    @JsonProperty("branch_name")
    @JsonView(Views.Api.class)
    private String branchName;

}
