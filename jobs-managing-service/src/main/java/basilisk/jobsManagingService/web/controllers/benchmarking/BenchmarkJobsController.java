package basilisk.jobsManagingService.web.controllers.benchmarking;

import basilisk.jobsManagingService.model.benchmarking.BenchmarkJob;
import basilisk.jobsManagingService.model.benchmarking.DockerBenchmarkJob;
import basilisk.jobsManagingService.model.benchmarking.GitBenchmarkJob;
import basilisk.jobsManagingService.services.benchmarking.BenchmarkJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BenchmarkJob>> getAllJobs() {
        return new ResponseEntity<>(this.benchmarkJobService.getAllBenchmarkJobs(), HttpStatus.OK);
    }

    @GetMapping("/docker")
    public ResponseEntity<List<DockerBenchmarkJob>> getAllDockerJobs() {
        return new ResponseEntity<>(this.benchmarkJobService.getAllDockerBenchmarkJobs(), HttpStatus.OK);
    }

    @GetMapping("/git")
    public ResponseEntity<List<GitBenchmarkJob>> getAllGitJobs() {
        return new ResponseEntity<>(this.benchmarkJobService.getAllGitBenchmarkJobs(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> abortBenchmarkJob(@RequestBody long jobId) {
        this.benchmarkJobService.abortJob(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
