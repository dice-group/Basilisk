package org.dicegroup.basilisk.events.benchmark;

import lombok.*;
import org.dicegroup.basilisk.dto.benchmark.BenchmarkDto;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockerBenchmarkJobCreateEvent implements Serializable {

    private DockerRepoDto repo;
    private String tagName;
    private String imageDigest;

    private LocalDate createDate;

    private BenchmarkDto benchmark;

}
