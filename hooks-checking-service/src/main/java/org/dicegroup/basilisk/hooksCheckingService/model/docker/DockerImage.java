package org.dicegroup.basilisk.hooksCheckingService.model.docker;


import org.dicegroup.basilisk.hooksCheckingService.model.BaseEntity;
import lombok.*;

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
