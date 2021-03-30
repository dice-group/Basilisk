package basilisk.hooksCheckingService.events;

import basilisk.hooksCheckingService.domain.docker.DockerImage;
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
public class DockerTagUpdatedEvent  implements Serializable {

    private String name;
    private long imageId;

    private Date lastPushedDate;

}
