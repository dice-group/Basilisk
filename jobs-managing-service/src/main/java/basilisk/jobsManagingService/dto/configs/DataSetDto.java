package basilisk.jobsManagingService.dto.configs;

import basilisk.jobsManagingService.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;

    @JsonProperty("url")
    private String url;

}
