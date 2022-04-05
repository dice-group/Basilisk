package org.dicegroup.basilisk.events.hooks.hook;

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
    private String commit_sha1;

}
