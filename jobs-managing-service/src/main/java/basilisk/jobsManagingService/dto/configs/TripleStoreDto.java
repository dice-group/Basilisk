package basilisk.jobsManagingService.dto.configs;

import basilisk.jobsManagingService.dto.BaseDto;
import basilisk.jobsManagingService.dto.Views;
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
public class TripleStoreDto extends BaseDto {

    @JsonProperty("name")
    @JsonView(Views.Api.class)
    private String name;

    @JsonProperty("endpoint")
    @JsonView(Views.Api.class)
    private String endpoint;

    @JsonProperty("update_endpoint")
    @JsonView(Views.Api.class)
    private String updateEndpoint;

    @JsonProperty("requires_authentication")
    @JsonView(Views.Api.class)
    private boolean requiresAuthentication;

    @JsonProperty("username")
    @JsonView(Views.Api.class)
    private String username;

    @JsonProperty("password")
    @JsonView(Views.Api.class)
    private String password;

}
