package basilisk.benchmarkService.domain;

import lombok.Setter;

import java.time.LocalDateTime;


@Setter
public class DockerJobConfig extends BaseEntity{

    private long imageId;
    private String digest;
    private LocalDateTime imageCreationDate;
}
