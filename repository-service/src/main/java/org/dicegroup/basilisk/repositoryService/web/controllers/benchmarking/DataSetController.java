package org.dicegroup.basilisk.repositoryService.web.controllers.benchmarking;

import com.sun.istack.NotNull;
import org.dicegroup.basilisk.dto.benchmark.DataSetDto;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.DataSet;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.DataSetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("datasets")
public class DataSetController {

    private final DataSetService dcService;
    private final ModelMapper modelMapper;

    public DataSetController(DataSetService dcService, ModelMapper modelMapper) {
        this.dcService = dcService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DataSetDto>> getAllDataSetConfigs() {
        List<DataSetDto> dcDtos = convertToDtoList(this.dcService.getAllDataSets());
        return new ResponseEntity<>(dcDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataSetDto> addDataSetConfig(@RequestBody @NotNull DataSetDto qcDto) {
        DataSet createdQc = this.dcService.addDataSet(convertToEntity(qcDto));
        return new ResponseEntity<>(convertToDto(createdQc), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDataSetConfig(@PathVariable @NotNull Long id) {
        Optional<DataSet> qc = this.dcService.getDataSet(id);
        if (qc.isPresent()) {
            this.dcService.deleteDataSetConfig(qc.get());
            return new ResponseEntity<>("The DataSetConfig has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no DataSetConfig with ID " + id, HttpStatus.OK);
        }
    }

    private List<DataSetDto> convertToDtoList(List<DataSet> qc) {
        return qc.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DataSetDto convertToDto(DataSet qc) {
        return this.modelMapper.map(qc, DataSetDto.class);
    }

    private DataSet convertToEntity(DataSetDto qcDto) {
        return this.modelMapper.map(qcDto, DataSet.class);
    }
}
