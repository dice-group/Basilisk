package basilisk.hooksCheckingService.domain.git;


import basilisk.hooksCheckingService.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "git_hook")
public class GitHook extends BaseEntity {

    @Column(name = "commit_creation_date")
    private Date commitCreationDate;
    @Column(name = "commit_sha1")
    private String commitSha1;
    @Column(name = "url")
    private String commitUrl;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private GitRepo gitRepo;

}
