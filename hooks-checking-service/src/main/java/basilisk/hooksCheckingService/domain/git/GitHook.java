package basilisk.hooksCheckingService.domain.git;


import basilisk.hooksCheckingService.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "git_hook")
public class GitHook extends BaseEntity {


    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private GitHookType type;

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
