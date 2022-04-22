package org.dicegroup.basilisk.benchmarkService.model.dockerContainer;

import lombok.*;
import org.dicegroup.basilisk.benchmarkService.model.BaseEntity;

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
    private String containerName;
    private ContainerStatus containerStatus;

    private Integer exposedPort;
    private Integer hostPort;

    private String dataSetHostPath;
    private String dataSetPath;

    private String entryPoint;

}
