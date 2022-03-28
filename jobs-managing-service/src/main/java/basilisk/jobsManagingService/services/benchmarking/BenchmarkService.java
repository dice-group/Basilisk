package basilisk.jobsManagingService.services.benchmarking;


import basilisk.jobsManagingService.model.benchmarking.Benchmark;
import basilisk.jobsManagingService.repositories.benchmarking.BenchmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BenchmarkService {

    private final BenchmarkRepository repo;

    public BenchmarkService(BenchmarkRepository repo) {
        this.repo = repo;
    }

    public Benchmark addBenchmark(Benchmark benchmark) {
        if (benchmark.getId()!= null) {
            Optional<Benchmark> oldBenchmark = getBenchmark(benchmark.getId());

            if (oldBenchmark.isPresent()) {
                benchmark = updateBenchmark(oldBenchmark.get(), benchmark);
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

    private Benchmark updateBenchmark(Benchmark oldBenchmark, Benchmark updatedBenchmark) {
        if (updatedBenchmark.getName() != null) {
            oldBenchmark.setName(updatedBenchmark.getName());
        }

        if (updatedBenchmark.getDataSet() != null) {
            oldBenchmark.setDataSet(updatedBenchmark.getDataSet());
        }

        if (updatedBenchmark.getQueryFileUrl() != null) {
            oldBenchmark.setQueryFileUrl(updatedBenchmark.getQueryFileUrl());
        }

        return oldBenchmark;
    }
}
