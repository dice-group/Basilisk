package basilisk.jobsManagingService.services;

import basilisk.jobsManagingService.model.TripleStore;
import basilisk.jobsManagingService.repositories.TripleStoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripleStoreService {

    private final TripleStoreRepository tripleStoreRepository;

    public TripleStoreService(TripleStoreRepository tripleStoreRepository) {
        this.tripleStoreRepository = tripleStoreRepository;
    }

    public TripleStore addTripleStore(TripleStore tripleStore) {
        return this.tripleStoreRepository.save(tripleStore);
    }

    public Optional<TripleStore> getTripleStore(Long id) {
        return this.tripleStoreRepository.findById(id);
    }

    public List<TripleStore> getAllTripleStores() {
        return (List<TripleStore>) this.tripleStoreRepository.findAll();
    }

    public void deleteTripleStore(TripleStore ts) {
        this.tripleStoreRepository.delete(ts);
    }
}
