package basilisk.jobsManagingService.events.hooks;

import basilisk.jobsManagingService.model.repo.DockerRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DockerRepoDeletedEvent {

    private DockerRepo repo;

}
