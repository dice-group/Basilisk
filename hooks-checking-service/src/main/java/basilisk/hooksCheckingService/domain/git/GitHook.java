package basilisk.hooksCheckingService.domain.git;


import basilisk.hooksCheckingService.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "git-hook")
public class GitHook extends BaseEntity {


    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private GitHookType type;

    @Column(name = "commit-creation-date")
    private Date commitCreationDate;
    @Column(name = "commit-sha1")
    private String commitSha1;
    @Column(name = "url")
    private String commitUrl;

    @ManyToOne
    @JoinColumn(name = "repo-id")
    private GitRepo gitRepo;


}
