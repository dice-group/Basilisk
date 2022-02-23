package basilisk.hooksCheckingService.model.git;


import basilisk.hooksCheckingService.model.Repo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitRepo extends Repo {

    private GitRepoType repoType;
    private String branchName;

}
