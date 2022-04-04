package org.dicegroup.basilisk.jobsManagingService.dto.configs;

import org.dicegroup.basilisk.jobsManagingService.dto.BaseDto;
import org.dicegroup.basilisk.jobsManagingService.dto.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataSetDto extends BaseDto {

    @JsonProperty("name")
    @JsonView(Views.Api.class)
    private String name;

    @JsonProperty("url")
    @JsonView(Views.Api.class)
    private String url;

}
