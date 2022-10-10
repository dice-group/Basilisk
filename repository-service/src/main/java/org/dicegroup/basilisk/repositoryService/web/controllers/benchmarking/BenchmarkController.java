package org.dicegroup.basilisk.repositoryService.web.controllers.benchmarking;


import com.sun.istack.NotNull;
import org.dicegroup.basilisk.dto.benchmark.BenchmarkDto;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.Benchmark;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.DataSet;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkService;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.DataSetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("benchmarks")
public class BenchmarkController {

    private final BenchmarkService service;
    private final DataSetService dsService;
    private final ModelMapper mapper;

    public BenchmarkController(BenchmarkService service, DataSetService dsService, ModelMapper mapper) {
        this.service = service;
        this.dsService = dsService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BenchmarkDto>> getAllBenchmarks() {
        List<BenchmarkDto> bmDtos = convertToDtoList(this.service.getAllBenchmarks());
        return new ResponseEntity<>(bmDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BenchmarkDto> addBenchmark(@RequestBody @NotNull BenchmarkDto bmDto) {
        Benchmark benchmark = convertToEntity(bmDto);
        if (benchmark.getDataSet() != null && benchmark.getDataSet().getId() != null) {
            Optional<DataSet> dsOptional = this.dsService.getDataSet(benchmark.getDataSet().getId());
            dsOptional.ifPresent(benchmark::setDataSet);
        }

        Benchmark createdBm = this.service.addBenchmark(benchmark);
        return new ResponseEntity<>(convertToDto(createdBm), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteBenchmark(@PathVariable @NotNull Long id) {
        Optional<Benchmark> bm = this.service.getBenchmark(id);
        if (bm.isPresent()) {
            this.service.deleteBenchmark(bm.get());
            return new ResponseEntity<>("The Benchmark has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no Benchmark with ID " + id, HttpStatus.OK);
        }
    }


    private List<BenchmarkDto> convertToDtoList(List<Benchmark> bm) {
        return bm.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BenchmarkDto convertToDto(Benchmark bm) {
        return this.mapper.map(bm, BenchmarkDto.class);
    }

    private Benchmark convertToEntity(BenchmarkDto bmDto) {
        return this.mapper.map(bmDto, Benchmark.class);
    }
}
