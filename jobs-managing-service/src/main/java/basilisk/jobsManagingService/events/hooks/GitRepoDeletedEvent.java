package basilisk.jobsManagingService.events.hooks;

import basilisk.jobsManagingService.model.repo.GitRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class GitRepoDeletedEvent {

    private GitRepo repo;

}
