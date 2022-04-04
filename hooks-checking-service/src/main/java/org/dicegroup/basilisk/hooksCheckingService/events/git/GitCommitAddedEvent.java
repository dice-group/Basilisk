package org.dicegroup.basilisk.hooksCheckingService.events.git;


import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitCommitAddedEvent implements Serializable {

    private long repoId;
    private String url;
    private String commit_sha1;
}
