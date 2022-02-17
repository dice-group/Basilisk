package basilisk.hooksCheckingService.events;


import lombok.*;

import java.io.Serializable;

/**
 * @author Fakhr Shaheen
 */

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
