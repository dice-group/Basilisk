package basilisk.hooksCheckingService.domain.HubRepos;




import basilisk.hooksCheckingService.domain.Hooks.Hook;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class HubRepo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "repo")
    private Set<Hook> hooks;

    public HubRepo()
    {;}

}
