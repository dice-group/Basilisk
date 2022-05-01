package org.dicegroup.basilisk.dto.benchmark;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.BaseDto;
import org.dicegroup.basilisk.dto.repo.RepoDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BenchmarkJobDto extends BaseDto {

    @JsonProperty("benchmark")
    private BenchmarkDto benchmark;

    @JsonProperty("status")
    private JobStatus status;

}
