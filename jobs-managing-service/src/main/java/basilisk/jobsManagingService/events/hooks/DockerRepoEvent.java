package basilisk.jobsManagingService.events.hooks;


import basilisk.jobsManagingService.model.repo.DockerRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class DockerRepoEvent implements Serializable {

    private RepoEventType eventType;
    private DockerRepo repo;

}
