package basilisk.hooksCheckingService.domain.git;


import basilisk.hooksCheckingService.domain.BaseEntity;
import basilisk.hooksCheckingService.domain.git.GitHook;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class GitRepo extends BaseEntity {

    @Column(name = "name")
    private String repoName;
    @Column(name = "owner")
    private String repoOwner;
    @Column(name = "is-private")
    private boolean isPrivate;
    @Column(name = "oAuth-token")
    private String OAuthToken;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hubRepo")
    private Set<GitHook> hooks;

    public GitRepo(String repoName, String repoOwner, boolean isPrivate, String OAuthToken) {
        this.repoName = repoName;
        this.repoOwner = repoOwner;
        this.isPrivate = isPrivate;
        this.OAuthToken = OAuthToken;
    }

    public GitRepo() {

    }
}