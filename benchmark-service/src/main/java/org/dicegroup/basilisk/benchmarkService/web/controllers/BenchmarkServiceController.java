package org.dicegroup.basilisk.benchmarkService.web.controllers;

import org.dicegroup.basilisk.benchmarkService.services.BenchmarkJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("benchmarkService/")
public class BenchmarkServiceController {

    private final BenchmarkJobService benchmarkJobService;

    public BenchmarkServiceController(BenchmarkJobService benchmarkJobService) {
        this.benchmarkJobService = benchmarkJobService;
    }

    @PostMapping(path = "/start")
    public String start() {
        if (this.benchmarkJobService.isRunning()) {
            return "the benchmark service is already running.";
        } else {
            this.benchmarkJobService.start();
            return "the benchmark service has started.";
        }
    }

    @PostMapping(path = "/stop")
    public String stop() {
        if (!this.benchmarkJobService.isRunning())
            return "The service is not running.";
        else {
            this.benchmarkJobService.stop();
            return "The service is stopped.";
        }

    }

    @GetMapping("/status")
    public String getStatus() {
        if (this.benchmarkJobService.isRunning()) {
            return "The service is running.";
        } else {
            return "The service is not running.";
        }
    }

}
