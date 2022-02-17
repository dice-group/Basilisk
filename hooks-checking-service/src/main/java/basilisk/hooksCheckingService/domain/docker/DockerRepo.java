package basilisk.hooksCheckingService.domain.docker;

import basilisk.hooksCheckingService.domain.BaseEntity;
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

    @Column(name = "name")
    private String repoName;
    @Column(name = "owner")
    private String repoOwner;
    @Column(name = "is_private")
    private boolean isPrivate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dockerRepo")
    private Set<DockerImage> dockerHooks;
}
