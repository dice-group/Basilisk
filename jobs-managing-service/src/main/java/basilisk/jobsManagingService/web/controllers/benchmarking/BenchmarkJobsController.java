package basilisk.jobsManagingService.web.controllers.benchmarking;

import basilisk.jobsManagingService.services.benchmarking.BenchmarkingJobsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("benchmarkJob")
public class BenchmarkJobsController {

    private final BenchmarkingJobsService benchmarkingJobsService;

    public BenchmarkJobsController(BenchmarkingJobsService benchmarkingJobsService)
    {
        this.benchmarkingJobsService=benchmarkingJobsService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> addDockerRepo(@RequestBody long jobId)
    {
        this.benchmarkingJobsService.abortJob(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
