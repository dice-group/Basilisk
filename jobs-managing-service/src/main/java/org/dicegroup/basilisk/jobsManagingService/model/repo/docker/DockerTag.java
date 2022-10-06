package org.dicegroup.basilisk.jobsManagingService.model.repo.docker;


import lombok.*;
import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;

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
