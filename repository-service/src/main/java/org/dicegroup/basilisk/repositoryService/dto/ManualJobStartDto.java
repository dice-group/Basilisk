package org.dicegroup.basilisk.repositoryService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManualJobStartDto {

    @JsonProperty("repo_id")
    private Long repoId;

    @JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("benchmark_id")
    private Long benchmarkId;

}
