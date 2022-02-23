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

    public void addTripleStore(TripleStore tripleStore) {
        tripleStoreRepository.save(tripleStore);
    }

    public Optional<TripleStore> getTripleStore(Long id) {
        return tripleStoreRepository.findById(id);
    }

    public List<TripleStore> getAllTripleStores() {
        return (List<TripleStore>) tripleStoreRepository.findAll();
    }
}
