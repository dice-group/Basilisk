package basilisk.hooksCheckingService.domain.Hooks;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GitHook extends Hook {
    Date updatedDate;
    Date createdDate;
    String sha1Hash;
    String url;


}
