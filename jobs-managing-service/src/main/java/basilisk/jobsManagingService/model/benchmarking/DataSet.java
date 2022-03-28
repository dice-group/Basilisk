package basilisk.jobsManagingService.model.benchmarking;

import basilisk.jobsManagingService.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataSet extends BaseEntity {

    String name;
    String url;

}
