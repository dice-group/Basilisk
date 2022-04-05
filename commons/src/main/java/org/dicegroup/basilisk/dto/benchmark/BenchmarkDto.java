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
public class BenchmarkDto extends BaseDto {

    @JsonProperty("name")
    @JsonView(Views.Api.class)
    private String name;

    @JsonProperty("query_file_url")
    @JsonView(Views.Api.class)
    private String queryFileUrl;

    @JsonProperty("dataset")
    @JsonView(Views.Api.class)
    private DataSetDto dataSet;

}
