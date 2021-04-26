package basilisk.jobsManagingService.services;

import basilisk.jobsManagingService.domain.TripleStore;
import basilisk.jobsManagingService.repositories.TripleStoreRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Fakhr Shaheen
 */
@Component
public class TripleStoreServiceImpl implements TripleStoreService{

    private TripleStoreRepository tripleStoreRepository;

    public TripleStoreServiceImpl(TripleStoreRepository tripleStoreRepository) {
        this.tripleStoreRepository = tripleStoreRepository;
    }

    @Override
    public void addTripleStore(TripleStore tripleStore) {
        tripleStoreRepository.save(tripleStore);
    }

    @Override
    public Optional<TripleStore> getTripleStore(Long id) {
        return tripleStoreRepository.findById(id);
    }

    @Override
    public Optional<TripleStore> getTripleStoreByGitRepoId(Long id) {
        return tripleStoreRepository.findTripleStoreByGitRepo_Id(id);
    }

    @Override
    public List<TripleStore> getAllTripleStores() {
        return (List<TripleStore>) tripleStoreRepository.findAll();
    }
}
