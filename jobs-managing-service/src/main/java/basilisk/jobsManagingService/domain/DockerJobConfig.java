package basilisk.jobsManagingService.domain;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Fakhr Shaheen
 */

@SuperBuilder
@Setter
@AllArgsConstructor
public class DockerJobConfig extends BaseEntity{

    private long imageId;
    private String digest;
    private LocalDateTime imageCreationDate;
}
