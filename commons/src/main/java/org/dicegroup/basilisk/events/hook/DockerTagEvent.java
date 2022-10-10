package org.dicegroup.basilisk.events.hook;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DockerTagEvent implements Serializable {

    private Long repoId;
    private String tagName;
    private String imageDigest;

}
