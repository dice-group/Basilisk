package org.dicegroup.basilisk.jobsManagingService.events;

import org.dicegroup.basilisk.jobsManagingService.model.repo.DockerRepo;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockerTagEvent implements Serializable {

    private DockerRepo repo;

    private String tagName;

    private String imageDigest;

}
