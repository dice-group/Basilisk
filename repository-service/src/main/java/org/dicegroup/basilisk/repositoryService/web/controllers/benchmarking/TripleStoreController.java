package org.dicegroup.basilisk.repositoryService.web.controllers.benchmarking;

import com.sun.istack.NotNull;
import org.dicegroup.basilisk.dto.benchmark.TripleStoreDto;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.TripleStore;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.TripleStoreService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("triplestore")
public class TripleStoreController {

    private final TripleStoreService tsService;
    private final ModelMapper modelMapper;

    public TripleStoreController(TripleStoreService tsService, ModelMapper mapper) {
        this.tsService = tsService;
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TripleStoreDto>> getAllTs() {
        List<TripleStoreDto> tsDtos = convertToDtoList(this.tsService.getAllTripleStores());
        return new ResponseEntity<>(tsDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TripleStoreDto> addTs(@RequestBody @NotNull TripleStoreDto tsDto) {
        TripleStore createdTs = this.tsService.addTripleStore(convertToEntity(tsDto));
        return new ResponseEntity<>(convertToDto(createdTs), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTs(@PathVariable @NotNull Long id) {
        Optional<TripleStore> ts = this.tsService.getTripleStore(id);
        if (ts.isPresent()) {
            this.tsService.deleteTripleStore(ts.get());
            return new ResponseEntity<>("The TripleStore has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no TripleStore with ID " + id, HttpStatus.OK);
        }
    }


    private List<TripleStoreDto> convertToDtoList(List<TripleStore> ts) {
        return ts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TripleStoreDto convertToDto(TripleStore ts) {
        return this.modelMapper.map(ts, TripleStoreDto.class);
    }

    private TripleStore convertToEntity(TripleStoreDto tsDto) {
        return this.modelMapper.map(tsDto, TripleStore.class);
    }
}
