package basilisk.hooksCheckingService.events.git;

import basilisk.hooksCheckingService.model.git.GitRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepoDeletedEvent {

    private GitRepo repo;

}
