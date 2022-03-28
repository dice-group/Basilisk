package basilisk.jobsManagingService.repositories.benchmarking;


import basilisk.jobsManagingService.model.benchmarking.DataSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataSetRepository extends CrudRepository<DataSet, Long> {

}
