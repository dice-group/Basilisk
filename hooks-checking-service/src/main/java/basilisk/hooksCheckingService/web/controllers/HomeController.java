package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.services.checkingServices.CheckingService;
import basilisk.hooksCheckingService.web.services.continuesCheckingServices.ContinuesCheckingService;
import basilisk.hooksCheckingService.web.services.continuesCheckingServices.ContinuesCheckingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Fakhr Shaheen
 */

@RestController
public class HomeController {
    private ContinuesCheckingService continuesCheckingService;

    public HomeController(ContinuesCheckingService continuesCheckingService) {
        this.continuesCheckingService=continuesCheckingService;
    }


    @GetMapping("/start")
    public String start() throws InterruptedException {
        if(continuesCheckingService.isRunning())
            return "already running";
        else
        {
            continuesCheckingService.start();
            return "started";
        }
    }

    @GetMapping("/stop")
    public String stop() {
        if(! continuesCheckingService.isRunning())
            return "The service is not running";
        else
        {
            continuesCheckingService.stop();
            return "The service is stopped";
        }
    }
}
