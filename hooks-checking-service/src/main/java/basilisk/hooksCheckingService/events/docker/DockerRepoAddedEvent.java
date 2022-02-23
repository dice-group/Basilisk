package basilisk.hooksCheckingService.events.docker;


import basilisk.hooksCheckingService.model.docker.DockerRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DockerRepoAddedEvent implements Serializable {

    private DockerRepo repo;

}
