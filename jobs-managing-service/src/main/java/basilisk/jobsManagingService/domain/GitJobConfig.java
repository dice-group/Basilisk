package basilisk.jobsManagingService.domain;

import java.util.Date;

/**
 * @author Fakhr Shaheen
 */
public class GitJobConfig extends BaseEntity{

    private String url;
    private String commit_sha1;
    private Date commitCreationDate;
}
