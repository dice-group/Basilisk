package basilisk.hooksCheckingService.model.git;


import basilisk.hooksCheckingService.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "git_hook")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitHook extends BaseEntity {

    private Date commitCreationDate;
    private String commitSha1;
    private String commitUrl;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private GitRepo gitRepo;
}
