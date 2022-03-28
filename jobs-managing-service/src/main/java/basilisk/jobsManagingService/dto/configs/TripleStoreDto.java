package basilisk.jobsManagingService.dto.configs;

import basilisk.jobsManagingService.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("update_endpoint")
    private String updateEndpoint;

    @JsonProperty("requires_authentication")
    private boolean requiresAuthentication;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}
