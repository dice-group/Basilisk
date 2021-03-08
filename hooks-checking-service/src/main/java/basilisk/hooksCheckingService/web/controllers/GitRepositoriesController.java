package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.dto.git.GitBranchRepoPostDto;
import basilisk.hooksCheckingService.dto.git.GitRepoPostDto;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fakhr Shaheen
 */

@RestController
@RequestMapping("repositories/git")
public class GitRepositoriesController {

    private GitRepoRepository gitRepoRepository;
    private ModelMapper modelMapper;

    public GitRepositoriesController(GitRepoRepository gitRepoRepository, ModelMapper modelMapper) {
        this.gitRepoRepository = gitRepoRepository;
        this.modelMapper=modelMapper;
    }

    @PostMapping(path = "/release",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addGitRepoForRelease(@RequestBody GitRepoPostDto gitRepoPostDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoPostDto, GitRepo.class);
        gitRepo.setType(GitType.release);
        gitRepoRepository.save(gitRepo);
    }

    @PostMapping(path = "/pullRequest",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addGitRepoForPullRequest(@RequestBody GitRepoPostDto gitRepoPostDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoPostDto, GitRepo.class);
        gitRepo.setType(GitType.pull_request);
        gitRepoRepository.save(gitRepo);
    }

    @PostMapping(path = "/branch",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addGitRepoForPullBranch(@RequestBody GitBranchRepoPostDto gitBranchRepoPostDto)
    {
        GitRepo gitRepo = modelMapper.map(gitBranchRepoPostDto, GitBranchRepo.class);
        gitRepo.setType(GitType.branch);
        gitRepoRepository.save(gitRepo);
    }

}
