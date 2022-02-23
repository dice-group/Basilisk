package basilisk.jobsManagingService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GitJobConfig extends BaseEntity{

    private String url;
    private String commit_sha1;
}
