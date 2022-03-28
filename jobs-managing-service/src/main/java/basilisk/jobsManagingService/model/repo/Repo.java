package basilisk.jobsManagingService.model.repo;

import basilisk.jobsManagingService.model.BaseEntity;
import basilisk.jobsManagingService.model.benchmarking.TripleStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Repo extends BaseEntity {

    private String repoName;
    private String repoOwner;
    private boolean isPrivate;
    private String oAuthToken;

    @ManyToOne
    @JoinColumn(name = "triple_store_id")
    private TripleStore tripleStore;
}
