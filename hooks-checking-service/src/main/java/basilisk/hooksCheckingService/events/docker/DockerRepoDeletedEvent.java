package basilisk.hooksCheckingService.events.docker;

import basilisk.hooksCheckingService.model.docker.DockerRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DockerRepoDeletedEvent {

    private DockerRepo repo;

}
