package basilisk.jobsManagingService.events;

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
public class DockerImageCreatedEvent implements Serializable {

    private long imageId;
    private long repoId;
    private String digest;

}
