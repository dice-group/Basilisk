package basilisk.hooksCheckingService.events;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

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
