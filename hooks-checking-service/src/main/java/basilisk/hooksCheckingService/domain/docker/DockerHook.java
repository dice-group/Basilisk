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

    @Column(columnDefinition="tag")
    private String tag;
    @Column(columnDefinition = "digest")
    String digest;
    @Column(columnDefinition="url")
    String url;
    @Column(columnDefinition="creation_date")
    Date creationDate;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private DockerRepo dockerRepo;

}
