package basilisk.jobsManagingService.services.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.DataSet;
import basilisk.jobsManagingService.repositories.benchmarking.DataSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DataSetService {

    private final DataSetRepository repo;

    public DataSetService(DataSetRepository repo) {
        this.repo = repo;
    }

    public DataSet addDataSet(DataSet dataSet) {
        DataSet savedDataSet = this.repo.save(dataSet);

        // TODO Download file

        return savedDataSet;
    }

    public Optional<DataSet> getDataSet(Long id) {
        return this.repo.findById(id);
    }

    public List<DataSet> getAllDataSets() {
        return (List<DataSet>) this.repo.findAll();
    }

    public void deleteDataSetConfig(DataSet ds) {

        // TODO Delete file

        this.repo.delete(ds);
    }

}
