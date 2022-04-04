package org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking;


import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.DataSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataSetRepository extends CrudRepository<DataSet, Long> {

}
