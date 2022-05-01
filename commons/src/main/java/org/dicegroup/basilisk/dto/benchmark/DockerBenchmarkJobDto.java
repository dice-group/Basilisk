package org.dicegroup.basilisk.dto.benchmark;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DockerBenchmarkJobDto extends BenchmarkJobDto {

    @JsonProperty("repo")
    private DockerRepoDto repo;

    @JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("image_digest")
    private String imageDigest;

}
