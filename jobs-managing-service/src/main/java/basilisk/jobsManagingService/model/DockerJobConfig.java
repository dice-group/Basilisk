package basilisk.jobsManagingService.model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@SuperBuilder
@Setter
@AllArgsConstructor
public class DockerJobConfig extends BaseEntity{

    private long imageId;
    private String digest;
    private LocalDateTime imageCreationDate;
}
