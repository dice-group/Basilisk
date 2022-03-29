package basilisk.jobsManagingService.model.repo;

import basilisk.jobsManagingService.model.BaseEntity;
import basilisk.jobsManagingService.model.benchmarking.TripleStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance
public abstract class Repo extends BaseEntity {

    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String oAuthToken;

    @ManyToOne
    private TripleStore tripleStore;

}
