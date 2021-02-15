package basilisk.hooksCheckingService.domain.docker;

import basilisk.hooksCheckingService.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "docker_hook")
public class DockerHook extends BaseEntity {

    @Column(name="tag")
    private String tag;
    @Column(name = "digest")
    String digest;
    @Column(name="url")
    String url;
    @Column(name="creation_date")
    Date creationDate;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private DockerRepo dockerRepo;

}
