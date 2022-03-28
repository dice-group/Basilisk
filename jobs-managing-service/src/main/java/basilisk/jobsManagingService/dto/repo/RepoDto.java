package basilisk.jobsManagingService.dto.repo;

import basilisk.jobsManagingService.dto.BaseDto;
import basilisk.jobsManagingService.dto.Views;
import basilisk.jobsManagingService.model.benchmarking.TripleStore;
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
public abstract class RepoDto extends BaseDto {

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

    @JsonProperty("triple_store")
    @JsonView(Views.Api.class)
    private TripleStore tripleStore;

}
