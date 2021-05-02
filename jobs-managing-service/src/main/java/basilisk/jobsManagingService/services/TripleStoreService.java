package basilisk.jobsManagingService.services;

import basilisk.jobsManagingService.domain.TripleStore;

import java.util.List;
import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */
public interface TripleStoreService {

    /**
     * add a triple store  to the system
     * @param tripleStore
     */
    void addTripleStore(TripleStore tripleStore);

    /**
     * retrieve a triple store
     * @param id triple store's id
     * @return
     */
    Optional<TripleStore> getTripleStore(Long id);

    /**
     * retrieve a triple store
     * @param id triple store's git repository id
     * @return
     */
    Optional<TripleStore> getTripleStoreByGitRepoId(Long id);

    /**
     * retrieve all triple stores
     * @return
     */
    List<TripleStore> getAllTripleStores();

}
