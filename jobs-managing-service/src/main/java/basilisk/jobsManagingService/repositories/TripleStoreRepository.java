package basilisk.jobsManagingService.repositories;

import basilisk.jobsManagingService.domain.TripleStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */

@Repository
public interface TripleStoreRepository extends CrudRepository<TripleStore,Long> {

    Optional<TripleStore> findTripleStoreByGitRepoId(Long gitRepoId);
}
