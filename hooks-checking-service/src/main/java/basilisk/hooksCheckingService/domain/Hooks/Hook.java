package basilisk.hooksCheckingService.domain.Hooks;


import basilisk.hooksCheckingService.domain.HubRepos.HubRepo;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class Hook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    @ManyToOne
    private HubRepo hubRepo;

}
