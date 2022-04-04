package org.dicegroup.basilisk.jobsManagingService.services.benchmarking;


import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.Benchmark;
import org.dicegroup.basilisk.jobsManagingService.repositories.benchmarking.BenchmarkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BenchmarkService {

    private final BenchmarkRepository repo;
    private final ModelMapper mapper;

    public BenchmarkService(BenchmarkRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Benchmark addBenchmark(Benchmark benchmark) {
        if (benchmark.getId()!= null) {
            Optional<Benchmark> oldBenchmark = getBenchmark(benchmark.getId());

            if (oldBenchmark.isPresent()) {
                Benchmark bm = oldBenchmark.get();
                this.mapper.map(benchmark, bm);
                benchmark = bm;
            }
        }

        Benchmark savedBenchmark = this.repo.save(benchmark);

        // TODO Download queryfile

        return savedBenchmark;
    }

    public Optional<Benchmark> getBenchmark(Long id) {
        return this.repo.findById(id);
    }

    public List<Benchmark> getAllBenchmarks() {
        return (List<Benchmark>) this.repo.findAll();
    }

    public void deleteBenchmark(Benchmark benchmark) {
        // TODO Delete queryfile

        this.repo.delete(benchmark);
    }
}
