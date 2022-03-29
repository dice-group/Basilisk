package basilisk.hooksCheckingService.model.docker;

import basilisk.hooksCheckingService.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DockerTag extends BaseEntity {

    private String name;

    @ManyToOne
    private DockerRepo dockerRepo;

    @ManyToOne
    private DockerImage dockerImage;

}
