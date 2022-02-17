package basilisk.jobsManagingService.events;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GitCommitAddedEvent implements Serializable {

    private long repoId;
    private String url;
    private String commit_sha1;
}
