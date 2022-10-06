package org.dicegroup.basilisk.repositoryService.repositories.benchmarking;

import org.dicegroup.basilisk.repositoryService.model.benchmarking.TripleStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TripleStoreRepository extends CrudRepository<TripleStore,Long> {
}
