package basilisk.hooksCheckingService.domain.git;


import basilisk.hooksCheckingService.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "git_repo")
@DiscriminatorColumn(
        name="is_branch",
        discriminatorType =DiscriminatorType.STRING)
@DiscriminatorValue("no")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class GitRepo extends BaseEntity {

    @Column(name = "name")
    private String repoName;
    @Column(name = "owner")
    private String repoOwner;
    @Column(name = "is_private")
    private boolean isPrivate;
    @Column(name = "oAuth_token")
    private String OAuthToken;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private GitType type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gitRepo")
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
