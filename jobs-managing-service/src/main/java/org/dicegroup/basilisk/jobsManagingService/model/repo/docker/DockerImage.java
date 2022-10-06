package org.dicegroup.basilisk.jobsManagingService.model.repo.docker;

import lombok.*;
import org.dicegroup.basilisk.jobsManagingService.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DockerImage extends BaseEntity {

    private String digest;

    @ManyToOne
    private DockerRepo dockerRepo;
}
