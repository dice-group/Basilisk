package basilisk.hooksCheckingService.events.docker;

import basilisk.hooksCheckingService.model.docker.DockerRepo;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockerTagEvent implements Serializable {

    private DockerRepo repo;

    private String tagName;

    private String imageDigest;

}
