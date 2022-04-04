package org.dicegroup.basilisk.jobsManagingService.events;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Fakhr Shaheen
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockerImageCreatedEvent implements Serializable {

    private long imageId;
    private long repoId;
    private String digest;
    private LocalDateTime imageCreationDate;

}
