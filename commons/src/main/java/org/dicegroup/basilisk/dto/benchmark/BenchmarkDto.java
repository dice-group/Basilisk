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

    @JsonProperty("query_file_path")
    @JsonView(Views.Api.class)
    private String queryFilePath;

    @JsonProperty("dataset")
    @JsonView(Views.Api.class)
    private DataSetDto dataSet;

    @JsonProperty("task_time_limit")
    @JsonView(Views.Api.class)
    private Long taskTimeLimit;
    @JsonProperty("no_of_query_mixes")
    @JsonView(Views.Api.class)
    private Integer noOfQueryMixes;
    @JsonProperty("worker_thread_count")
    @JsonView(Views.Api.class)
    private Integer workerThreadCount;
    @JsonProperty("worker_timeout")
    @JsonView(Views.Api.class)
    private Long workerTimeOut;

}
