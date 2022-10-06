package org.dicegroup.basilisk.repositoryService.web.controllers.repo;


import org.dicegroup.basilisk.repositoryService.services.repo.continuesCheckingServices.ContinuousCheckingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("continuousChecking/")
public class ContinuousCheckingController {

    private final ContinuousCheckingService continuesCheckingService;

    public ContinuousCheckingController(ContinuousCheckingService continuesCheckingService) {
        this.continuesCheckingService = continuesCheckingService;
    }

    @PostMapping(path = "/start")
    public String start() throws InterruptedException {
        if (this.continuesCheckingService.isRunning()) {
            return "the service is already running.";
        } else {
            this.continuesCheckingService.start();
            return "the service has started.";
        }
    }

    @PostMapping(path = "/stop")
    public String stop() {
        if (!this.continuesCheckingService.isRunning())
            return "The service is not running.";
        else {
            this.continuesCheckingService.stop();
            return "The service is stopped.";
        }

    }

    @GetMapping("/status")
    public String getStatus() {
        if (this.continuesCheckingService.isRunning()) {
            return "The service is running.";
        } else {
            return "The service is not running.";
        }
    }


}
