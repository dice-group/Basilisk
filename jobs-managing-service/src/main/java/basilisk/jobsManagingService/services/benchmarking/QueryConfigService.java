package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.QueryConfig;
import basilisk.jobsManagingService.repositories.benchmarking.QueryConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryConfigService {

    private final QueryConfigRepository qcRepo;

    public QueryConfigService(QueryConfigRepository qcRepo) {
        this.qcRepo = qcRepo;
    }

    public QueryConfig addQueryConfig(QueryConfig queryConfig) {
        return this.qcRepo.save(queryConfig);
    }

    public Optional<QueryConfig> getQueryConfig(Long id) {
        return this.qcRepo.findById(id);
    }

    public List<QueryConfig> getAllQueryConfigs() {
        return (List<QueryConfig>) this.qcRepo.findAll();
    }

    public List<QueryConfig> getAllActiveQueryConfigs() {
        return this.qcRepo.findAllByActive(true);
    }

    public void deleteQueryConfig(QueryConfig qc) {
        this.qcRepo.delete(qc);
    }

}
