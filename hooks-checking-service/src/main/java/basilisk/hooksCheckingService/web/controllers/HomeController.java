package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.services.CheckingService;
import basilisk.hooksCheckingService.web.services.git.GitBranchCheckingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public HomeController(GitHookRepository gitHookRepository, GitRepoRepository gitRepoRepository, CheckingService checkingService) {
        this.gitHookRepository = gitHookRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.checkingService = checkingService;
    }

    @GetMapping("/index")
    public String getIndexPage() throws IOException {
        checkingService.performChecking();
        return "";
    }
    ;
}
