package org.dicegroup.basilisk.dto.benchmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.BaseDto;
import org.dicegroup.basilisk.dto.Views;

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

    @JsonProperty("exposed_port")
    @JsonView(Views.Api.class)
    private int exposedPort;

    @JsonProperty("dataset_path")
    @JsonView(Views.Api.class)
    private String dataSetPath;

    @JsonProperty("entry_point")
    @JsonView(Views.Api.class)
    private String entryPoint;

    @JsonProperty("pre_script_hook")
    @JsonView(Views.Api.class)
    private String preScriptHook;

}
