package org.dicegroup.basilisk.repositoryService.web.controllers.benchmarking;

import com.sun.istack.NotNull;
import org.dicegroup.basilisk.repositoryService.dto.ManualJobStartDto;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.BenchmarkJob;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.DockerBenchmarkJob;
import org.dicegroup.basilisk.repositoryService.model.benchmarking.GitBenchmarkJob;
import org.dicegroup.basilisk.repositoryService.services.benchmarking.BenchmarkJobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/pending")
    public List<BenchmarkJob> getAllPendingJobs() {
        return this.benchmarkJobService.getAllPendingJobs();
    }

    @GetMapping("/docker")
    public List<DockerBenchmarkJob> getAllDockerJobs() {
        return this.benchmarkJobService.getAllDockerBenchmarkJobs();
    }

    @GetMapping("/git")
    public List<GitBenchmarkJob> getAllGitJobs() {
        return this.benchmarkJobService.getAllGitBenchmarkJobs();
    }

    @PostMapping(path = "/manualStart", consumes = "application/json")
    public String manualStart(@RequestBody ManualJobStartDto startDto) {
        return this.benchmarkJobService.createManualBenchmarkJob(startDto.getRepoId(), startDto.getTagName(), startDto.getBenchmarkId());
    }

    @PostMapping(path = "/abort/{id}")
    public String abortBenchmarkJob(@PathVariable @NotNull Long id) {
        return this.benchmarkJobService.abortJob(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteBenchmarkJob(@PathVariable @NotNull Long id) {
        Optional<BenchmarkJob> jobOpt = this.benchmarkJobService.getBenchmarkJob(id);
        if (jobOpt.isPresent()) {
            this.benchmarkJobService.deleteBenchmarkJob(jobOpt.get());
            return "Deleted Job";
        } else {
            return "Job not found!";
        }
    }

}
