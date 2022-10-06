package org.dicegroup.basilisk.repositoryService.repositories.benchmarking;


import org.dicegroup.basilisk.repositoryService.model.benchmarking.DataSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataSetRepository extends CrudRepository<DataSet, Long> {

}
