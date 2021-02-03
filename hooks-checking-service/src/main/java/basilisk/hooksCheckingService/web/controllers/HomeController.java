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
    private GitHookRepository gitHookRepository;
    private GitRepoRepository gitRepoRepository;
    private CheckingService checkingService;
    private ContinuesCheckingService continuesCheckingService;

    public HomeController(GitHookRepository gitHookRepository, GitRepoRepository gitRepoRepository, CheckingService checkingService,ContinuesCheckingService continuesCheckingService) {
        this.gitHookRepository = gitHookRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.checkingService = checkingService;
        this.continuesCheckingService=continuesCheckingService;
    }

    @GetMapping("/index")
    public String getIndexPage() throws IOException {
        checkingService.performChecking();
        return "";
    }

    @GetMapping("/start")
    public String start() throws IOException, InterruptedException {
        continuesCheckingService.check();
        return "";
    }
}
