package basilisk.hooksCheckingService.events.docker;


import basilisk.hooksCheckingService.events.RepoEventType;
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
public class DockerRepoEvent implements Serializable {

    private RepoEventType eventType;
    private DockerRepo repo;

}