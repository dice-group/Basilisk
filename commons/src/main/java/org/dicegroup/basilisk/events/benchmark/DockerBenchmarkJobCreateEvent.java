package org.dicegroup.basilisk.events.benchmark;

import lombok.*;
import org.dicegroup.basilisk.dto.benchmark.BenchmarkDto;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockerBenchmarkJobCreateEvent implements Serializable {

    private Long jobId;

    private DockerRepoDto repo;
    private String tagName;
    private String imageDigest;

    private BenchmarkDto benchmark;

}
