package basilisk.jobsManagingService.dto.configs;


import basilisk.jobsManagingService.dto.BaseDto;
import basilisk.jobsManagingService.dto.Views;
import basilisk.jobsManagingService.model.benchmarking.DataSet;
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
public class BenchmarkDto extends BaseDto {

    @JsonProperty("name")
    @JsonView(Views.Api.class)
    private String name;

    @JsonProperty("query_file_url")
    @JsonView(Views.Api.class)
    private String queryFileUrl;

    @JsonProperty("dataset")
    @JsonView(Views.Api.class)
    private DataSet dataSet;

}
