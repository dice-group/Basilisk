package basilisk.hooksCheckingService.model.docker;

import basilisk.hooksCheckingService.model.Repo;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
@AllArgsConstructor
public class DockerRepo extends Repo {

}
