package org.dicegroup.basilisk.jobsManagingService.web.controllers.benchmarking;

import com.sun.istack.NotNull;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.BenchmarkJob;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.DockerBenchmarkJob;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import org.dicegroup.basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("benchmarkJob")
public class BenchmarkJobsController {

    private final BenchmarkJobService benchmarkJobService;

    public BenchmarkJobsController(BenchmarkJobService benchmarkJobService) {
        this.benchmarkJobService = benchmarkJobService;
    }

    @GetMapping
    public List<BenchmarkJob> getAllJobs() {
        return this.benchmarkJobService.getAllBenchmarkJobs();
    }

    @GetMapping("/docker")
    public List<DockerBenchmarkJob> getAllDockerJobs() {
        return this.benchmarkJobService.getAllDockerBenchmarkJobs();
    }

    @GetMapping("/git")
    public List<GitBenchmarkJob> getAllGitJobs() {
        return this.benchmarkJobService.getAllGitBenchmarkJobs();
    }

    @PostMapping(path = "/{id}")
    public String abortBenchmarkJob(@PathVariable @NotNull Long id) {
        return this.benchmarkJobService.abortJob(id);
    }

}
