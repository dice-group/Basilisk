package basilisk.jobsManagingService.domain;

import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Fakhr Shaheen
 */

@SuperBuilder
@Setter
public class DockerJobConfig extends BaseEntity{

    private long imageId;
    private String digest;
    private LocalDateTime imageCreationDate;
}
