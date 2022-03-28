package basilisk.jobsManagingService.model.repo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GIT_REPO")
@Getter
@Setter
public class GitRepo extends Repo {

    private GitRepoType repoType;
    private String branchName;

}
