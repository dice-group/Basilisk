package basilisk.hooksCheckingService.events.docker;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockerTagAddedEvent  implements Serializable {

    private String name;
    private long imageId;

    private Date lastPushedDate;
}
