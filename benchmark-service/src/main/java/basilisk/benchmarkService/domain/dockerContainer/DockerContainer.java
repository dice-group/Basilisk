package basilisk.benchmarkService.domain.dockerContainer;

import basilisk.benchmarkService.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DockerContainer extends BaseEntity {

    private String imageId;
    private String imageName;
    private ImageStatus imageStatus;

    private String containerId;
    private ContainerStatus containerStatus;

}
