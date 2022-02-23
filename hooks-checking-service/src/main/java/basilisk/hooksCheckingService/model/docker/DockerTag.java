package basilisk.hooksCheckingService.model.docker;

import basilisk.hooksCheckingService.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "docker_tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DockerTag extends BaseEntity {


    private String name;

    @Column(name = "last_pushed")
    private Date lastPushedDate;

    @ManyToOne
    @JoinColumn(name = "docker_image_id")
    private DockerImage dockerImage;

}
