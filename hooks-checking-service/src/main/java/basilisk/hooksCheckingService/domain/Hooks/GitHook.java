package basilisk.hooksCheckingService.domain.Hooks;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Setter
@Entity
public class GitHook extends Hook {
    private Date updatedDate;
    private Date createdDate;
    private String sha1Hash;
    private String url;

    @Enumerated(value=EnumType.STRING)
    private GitType type;


}
