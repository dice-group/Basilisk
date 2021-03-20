package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.dto.git.GitBranchRepoDto;
import basilisk.hooksCheckingService.dto.git.GitRepoDto;
import basilisk.hooksCheckingService.services.HooksServices.GitHooksService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */

@RestController
@RequestMapping("hooks/git")
public class GitHooksController {

    private GitHooksService gitHooksService;
    private ModelMapper modelMapper;

    public GitHooksController(GitHooksService gitHooksService, ModelMapper modelMapper) {
        this.gitHooksService = gitHooksService;
        this.modelMapper=modelMapper;
    }



    @GetMapping("/release")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitRepoDto>> getllGitReleaseRepos() {
        List<GitRepoDto> gitRepoDtos=gitHooksService.findAllGitReleaseRepos();
        return new ResponseEntity<>(gitRepoDtos,HttpStatus.OK) ;
    }

    @GetMapping("/pullRequest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitRepoDto>> getAllGitPullRequestRepos() {
        var gitRepoDtos= gitHooksService.findAllGitPullRequestRepos();
        return new ResponseEntity<>(gitRepoDtos,HttpStatus.OK) ;
    }

    @GetMapping("/branch")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitBranchRepoDto>> getAllGitBranchRepos() {
        var gitRepoDtos= gitHooksService.findAllGitBranchRepos();
        return new ResponseEntity<>(gitRepoDtos,HttpStatus.OK) ;
    }



    @PostMapping(path = "/release",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitRepoDto> addGitRepoForRelease(@RequestBody GitRepoDto gitRepoDto)
    {
        var createdGitRepoDto=gitHooksService.addGitRepoForRelease(gitRepoDto);
        return new ResponseEntity<>(createdGitRepoDto,HttpStatus.OK) ;
    }

    @PostMapping(path = "/pullRequest",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitRepoDto> addGitRepoForPullRequest(@RequestBody GitRepoDto gitRepoDto)
    {
        var createdGitRepoDto=gitHooksService.addGitRepoForPullRequest(gitRepoDto);
        return new ResponseEntity<>(createdGitRepoDto,HttpStatus.OK) ;
    }

    @PostMapping(path = "/branch",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitBranchRepoDto> addGitRepoForBranch(@RequestBody GitBranchRepoDto gitBranchRepoPostDto)
    {
        var createdGitRepoDto=gitHooksService.addGitRepoForBranch(gitBranchRepoPostDto);
        return new ResponseEntity<>(createdGitRepoDto,HttpStatus.OK) ;
    }


}
