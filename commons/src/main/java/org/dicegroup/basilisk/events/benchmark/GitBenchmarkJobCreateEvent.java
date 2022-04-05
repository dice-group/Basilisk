package org.dicegroup.basilisk.events.benchmark;

import lombok.*;
import org.dicegroup.basilisk.dto.repo.GitRepoDto;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitBenchmarkJobCreateEvent implements Serializable {

    private GitRepoDto repo;
    private String url;
    private String commitSha1;

}
