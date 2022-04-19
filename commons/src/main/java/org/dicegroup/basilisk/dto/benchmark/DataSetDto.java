package org.dicegroup.basilisk.dto.benchmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.BaseDto;
import org.dicegroup.basilisk.dto.Views;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataSetDto extends BaseDto {

    @JsonProperty("name")
    @JsonView(Views.Api.class)
    private String name;

    @JsonProperty("file_path")
    @JsonView(Views.Api.class)
    private String filePath;

}
