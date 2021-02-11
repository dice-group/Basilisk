package basilisk.hooksCheckingService.domain.docker;

import basilisk.hooksCheckingService.domain.BaseEntity;
import basilisk.hooksCheckingService.domain.git.GitHook;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "docker_repo")
public class DockerRepo extends BaseEntity {

    @Column(columnDefinition="repo_name")
    private String repoName;
    @Column(columnDefinition="repo_owner")
    private String ownerName;
    @Column(columnDefinition="is_private")
    private boolean isPrivate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dockerRepo")
    private Set<DockerHook> dockerHooks;
}
