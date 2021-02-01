package basilisk.hooksCheckingService.domain.Hooks;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Hook {

    UUID id;
    Date createdDate;

}
