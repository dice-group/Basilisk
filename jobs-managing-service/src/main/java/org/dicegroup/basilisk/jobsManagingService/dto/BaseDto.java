package org.dicegroup.basilisk.jobsManagingService.dto;

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
public abstract class BaseDto {

    @JsonProperty("id")
    @JsonView(Views.Api.class)
    private Long id;

}
