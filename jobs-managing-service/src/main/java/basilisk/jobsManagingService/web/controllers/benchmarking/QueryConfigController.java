package basilisk.jobsManagingService.web.controllers.benchmarking;


import basilisk.jobsManagingService.dto.configs.QueryConfigDto;
import basilisk.jobsManagingService.model.benchmarking.QueryConfig;
import basilisk.jobsManagingService.services.benchmarking.QueryConfigService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/configs/query")
public class QueryConfigController {

    private final QueryConfigService qcService;
    private final ModelMapper modelMapper;

    public QueryConfigController(QueryConfigService qcService, ModelMapper modelMapper) {
        this.qcService = qcService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<QueryConfigDto>> getAllQueryConfigs() {
        List<QueryConfigDto> qcDtos = convertToDtoList(this.qcService.getAllQueryConfigs());
        return new ResponseEntity<>(qcDtos, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<QueryConfigDto>> getAllActiveQueryConfigs() {
        List<QueryConfigDto> qcDtos = convertToDtoList(this.qcService.getAllActiveQueryConfigs());
        return new ResponseEntity<>(qcDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<QueryConfigDto> addQueryConfig(@RequestBody @NotNull QueryConfigDto qcDto) {
        QueryConfig createdQc = this.qcService.addQueryConfig(convertToEntity(qcDto));
        return new ResponseEntity<>(convertToDto(createdQc), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteQueryConfig(@PathVariable @NotNull Long id) {
        Optional<QueryConfig> qc = this.qcService.getQueryConfig(id);
        if (qc.isPresent()) {
            this.qcService.deleteQueryConfig(qc.get());
            return new ResponseEntity<>("The QueryConfig has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no QueryConfig with ID " + id, HttpStatus.OK);
        }
    }

    private List<QueryConfigDto> convertToDtoList(List<QueryConfig> qc) {
        return qc.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private QueryConfigDto convertToDto(QueryConfig qc) {
        return this.modelMapper.map(qc, QueryConfigDto.class);
    }

    private QueryConfig convertToEntity(QueryConfigDto qcDto) {
        return this.modelMapper.map(qcDto, QueryConfig.class);
    }
}
