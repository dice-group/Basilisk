package basilisk.hooksCheckingService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Repo {

    @Id
    private Long id;
    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String oAuthToken;

}
