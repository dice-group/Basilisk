package org.dicegroup.basilisk.events.hook;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitCommitEvent implements Serializable {

    private Long repoId;
    private String url;
    private String commitSha1;

}
