package basilisk.jobsManagingService.services;

import basilisk.jobsManagingService.domain.TripleStore;

import java.util.List;
import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */
public interface TripleStoreService {

    void addTripleStore(TripleStore tripleStore);

    Optional<TripleStore> getTripleStore(Long id);

    Optional<TripleStore> getTripleStoreByGitRepoId(Long id);

    List<TripleStore> getAllTripleStores();

}
