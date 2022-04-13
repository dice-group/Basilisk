package org.dicegroup.basilisk.benchmarkService.domain.dockerContainer;

import lombok.*;
import org.dicegroup.basilisk.benchmarkService.domain.BaseEntity;

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
    private String imageDigest;
    private ImageStatus imageStatus;

    private String containerId;
    private ContainerStatus containerStatus;

}
