package basilisk.jobsManagingService.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Fakhr Shaheen
 */

@SuperBuilder
@Setter
@Getter
public class GitJobConfig extends BaseEntity{

    private String url;
    private String commit_sha1;
    private Date commitCreationDate;
}
