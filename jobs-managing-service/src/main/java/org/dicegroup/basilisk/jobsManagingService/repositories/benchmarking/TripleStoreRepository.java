package org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking;

import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.TripleStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TripleStoreRepository extends CrudRepository<TripleStore,Long> {
}
