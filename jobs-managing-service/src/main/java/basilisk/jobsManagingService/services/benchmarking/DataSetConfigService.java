package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.repositories.benchmarking.DataSetConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DataSetConfigService {

    private final DataSetConfigRepository dcRepo;

    public DataSetConfigService(DataSetConfigRepository dcRepo) {
        this.dcRepo = dcRepo;
    }

    public DataSetConfig addDataSetConfig(DataSetConfig config) {
        return this.dcRepo.save(config);
    }

    public Optional<DataSetConfig> getDataSetConfig(Long id) {
        return this.dcRepo.findById(id);
    }

    public List<DataSetConfig> getAllDataSetConfigs() {
        return (List<DataSetConfig>) this.dcRepo.findAll();
    }

    public List<DataSetConfig> getAllActiveDataSetConfigs() {
        return this.dcRepo.findAllByActive(true);
    }

    public void deleteDataSetConfig(DataSetConfig dc) {
        this.dcRepo.delete(dc);
    }

}
