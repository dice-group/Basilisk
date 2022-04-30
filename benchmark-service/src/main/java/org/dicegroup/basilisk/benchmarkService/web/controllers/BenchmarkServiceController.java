package org.dicegroup.basilisk.benchmarkService.web.controllers;

import org.dicegroup.basilisk.benchmarkService.services.jobExecution.JobExecutionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("benchmarkService/")
public class BenchmarkServiceController {

    private final JobExecutionService jobExecutionService;

    public BenchmarkServiceController(JobExecutionService jobExecutionService) {
        this.jobExecutionService = jobExecutionService;
    }

    @PostMapping(path = "/start")
    public String start() throws InterruptedException, IOException {
        if (this.jobExecutionService.isRunning()) {
            return "the benchmark service is already running.";
        } else {
            this.jobExecutionService.start();
            return "the benchmark service has started.";
        }
    }

    @PostMapping(path = "/stop")
    public String stop() {
        if (!this.jobExecutionService.isRunning())
            return "The service is not running.";
        else {
            this.jobExecutionService.stop();
            return "The service is stopped.";
        }

    }

    @GetMapping("/status")
    public String getStatus() {
        if (this.jobExecutionService.isRunning()) {
            return "The service is running.";
        } else {
            return "The service is not running.";
        }
    }

}
