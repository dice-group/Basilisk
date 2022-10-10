package org.dicegroup.basilisk.repositoryService.services.benchmarking;

import org.dicegroup.basilisk.repositoryService.model.benchmarking.DataSet;
import org.dicegroup.basilisk.repositoryService.repositories.benchmarking.DataSetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DataSetService {

    private final DataSetRepository repo;
    private final ModelMapper mapper;

    public DataSetService(DataSetRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public DataSet addDataSet(DataSet dataSet) {
        if (dataSet.getId() != null) {
            Optional<DataSet> oldDataSet = getDataSet(dataSet.getId());
            if (oldDataSet.isPresent()) {
                DataSet ds = oldDataSet.get();
                this.mapper.map(dataSet, ds);
                dataSet = ds;
            }
        }
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
