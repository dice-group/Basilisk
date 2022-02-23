package basilisk.hooksCheckingService.events.docker;

import lombok.*;

import java.io.Serializable;


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
