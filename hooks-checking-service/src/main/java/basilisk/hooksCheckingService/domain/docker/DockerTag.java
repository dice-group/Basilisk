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
@Table(name = "docker_tag")
public class DockerTag extends BaseEntity {


    @Column(name = "name")
    String name;

    @Column(name = "last_pushed")
    Date lastPushedDate;

    @ManyToOne()
    @JoinColumn(name = "image_id")
    DockerImage dockerImage;

}
