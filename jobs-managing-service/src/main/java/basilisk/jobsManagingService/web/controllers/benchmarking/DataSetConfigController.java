package basilisk.jobsManagingService.web.controllers.benchmarking;

import basilisk.jobsManagingService.dto.configs.DataSetConfigDto;
import basilisk.jobsManagingService.model.benchmarking.DataSetConfig;
import basilisk.jobsManagingService.services.benchmarking.DataSetConfigService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/configs/dataset")
public class DataSetConfigController {

    private final DataSetConfigService dcService;
    private final ModelMapper modelMapper;

    public DataSetConfigController(DataSetConfigService dcService, ModelMapper modelMapper) {
        this.dcService = dcService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DataSetConfigDto>> getAllDataSetConfigs() {
        List<DataSetConfigDto> dcDtos = convertToDtoList(this.dcService.getAllDataSetConfigs());
        return new ResponseEntity<>(dcDtos, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<DataSetConfigDto>> getAllActiveDataSetConfigs() {
        List<DataSetConfigDto> dcDtos = convertToDtoList(this.dcService.getAllActiveDataSetConfigs());
        return new ResponseEntity<>(dcDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataSetConfigDto> addDataSetConfig(@RequestBody @NotNull DataSetConfigDto qcDto) {
        DataSetConfig createdQc = this.dcService.addDataSetConfig(convertToEntity(qcDto));
        return new ResponseEntity<>(convertToDto(createdQc), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDataSetConfig(@PathVariable @NotNull Long id) {
        Optional<DataSetConfig> qc = this.dcService.getDataSetConfig(id);
        if (qc.isPresent()) {
            this.dcService.deleteDataSetConfig(qc.get());
            return new ResponseEntity<>("The DataSetConfig has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no DataSetConfig with ID " + id, HttpStatus.OK);
        }
    }

    private List<DataSetConfigDto> convertToDtoList(List<DataSetConfig> qc) {
        return qc.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DataSetConfigDto convertToDto(DataSetConfig qc) {
        return this.modelMapper.map(qc, DataSetConfigDto.class);
    }

    private DataSetConfig convertToEntity(DataSetConfigDto qcDto) {
        return this.modelMapper.map(qcDto, DataSetConfig.class);
    }
}
