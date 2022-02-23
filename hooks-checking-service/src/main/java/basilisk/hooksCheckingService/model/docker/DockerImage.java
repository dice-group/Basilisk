package basilisk.hooksCheckingService.model.docker;


import basilisk.hooksCheckingService.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "docker_image")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DockerImage extends BaseEntity {

    private String digest;

    @Column(name = "last_pushed")
    private Date lastPushedDate;

    @ManyToOne
    @JoinColumn(name = "docker_repo_id")
    private DockerRepo dockerRepo;
}
