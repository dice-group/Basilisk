package org.dicegroup.basilisk.repositoryService.model.repo.git;


import lombok.*;
import org.dicegroup.basilisk.repositoryService.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitHook extends BaseEntity {

    private Date commitCreationDate;
    private String commitSha1;
    private String commitUrl;

    @ManyToOne
    private GitRepo gitRepo;
}
