package org.dicegroup.basilisk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto implements Serializable {

    @JsonProperty("id")
    @JsonView(Views.Api.class)
    private Long id;

}
