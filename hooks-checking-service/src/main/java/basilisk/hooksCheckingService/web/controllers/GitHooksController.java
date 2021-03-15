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
        var repos= gitHooksService.findAllGitReleaseRepos();
        List<GitRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitRepoDto gitRepoDto=modelMapper.map(repo,GitRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return new ResponseEntity<>(gitRepoDtos,HttpStatus.OK) ;
    }

    @GetMapping("/pullRequest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitRepoDto>> getAllGitPullRequestRepos() {
        var repos= gitHooksService.findAllGitPullRequestRepos();
        List<GitRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitRepoDto gitRepoDto=modelMapper.map(repo,GitRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return new ResponseEntity<>(gitRepoDtos,HttpStatus.OK) ;
    }

    @GetMapping("/branch")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitBranchRepoDto>> getAllGitBranchRepos() {
        var repos= gitHooksService.findAllGitBranchRepos();
        List<GitBranchRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitBranchRepoDto gitRepoDto=modelMapper.map(repo,GitBranchRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return new ResponseEntity<>(gitRepoDtos,HttpStatus.OK) ;
    }



    @PostMapping(path = "/release",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitRepoDto> addGitRepoForRelease(@RequestBody GitRepoDto gitRepoDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoDto, GitRepo.class);
        gitHooksService.addGitRepoForRelease(gitRepo);
        return new ResponseEntity<>(gitRepoDto,HttpStatus.OK) ;
    }

    @PostMapping(path = "/pullRequest",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitRepoDto> addGitRepoForPullRequest(@RequestBody GitRepoDto gitRepoDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoDto, GitRepo.class);
        gitHooksService.addGitRepoForPullRequest(gitRepo);
        return new ResponseEntity<>(gitRepoDto,HttpStatus.OK) ;
    }

    @PostMapping(path = "/branch",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitBranchRepoDto> addGitRepoForPullBranch(@RequestBody GitBranchRepoDto gitBranchRepoPostDto)
    {
        GitRepo gitRepo = modelMapper.map(gitBranchRepoPostDto, GitBranchRepo.class);
        gitHooksService.addGitRepoForPullBranch(gitRepo);
        return new ResponseEntity<>(gitBranchRepoPostDto,HttpStatus.OK) ;
    }


}
