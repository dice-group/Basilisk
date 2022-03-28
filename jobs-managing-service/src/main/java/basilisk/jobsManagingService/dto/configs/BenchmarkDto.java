package basilisk.jobsManagingService.dto.configs;


import basilisk.jobsManagingService.dto.BaseDto;
import basilisk.jobsManagingService.model.benchmarking.DataSet;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BenchmarkDto extends BaseDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("query_file_url")
    private String queryFileUrl;

    @JsonProperty("dataset")
    private DataSet dataSet;

}
