package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.dto.git.GitBranchRepoDto;
import basilisk.hooksCheckingService.dto.git.GitRepoDto;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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



    @GetMapping("/release")
    public List<GitRepoDto> findAllGitReleaseRepos() {
        var repos= gitRepoRepository.findAllByType(GitType.release);
        List<GitRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitRepoDto gitRepoDto=modelMapper.map(repo,GitRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return gitRepoDtos;
    }

    @GetMapping("/pullRequest")
    public List<GitRepoDto> findAllGitPullRequestRepos() {
        var repos= gitRepoRepository.findAllByType(GitType.pull_request);
        List<GitRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitRepoDto gitRepoDto=modelMapper.map(repo,GitRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return gitRepoDtos;
    }

    @GetMapping("/branch")
    public List<GitBranchRepoDto> findAllGitBranchRepos() {
        var repos= gitRepoRepository.findAllByType(GitType.branch);
        List<GitBranchRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitBranchRepoDto gitRepoDto=modelMapper.map(repo,GitBranchRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return gitRepoDtos;
    }



    @PostMapping(path = "/release",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addGitRepoForRelease(@RequestBody GitRepoDto gitRepoDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoDto, GitRepo.class);
        gitRepo.setType(GitType.release);
        gitRepoRepository.save(gitRepo);
    }

    @PostMapping(path = "/pullRequest",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addGitRepoForPullRequest(@RequestBody GitRepoDto gitRepoDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoDto, GitRepo.class);
        gitRepo.setType(GitType.pull_request);
        gitRepoRepository.save(gitRepo);
    }

    @PostMapping(path = "/branch",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addGitRepoForPullBranch(@RequestBody GitBranchRepoDto gitBranchRepoPostDto)
    {
        GitRepo gitRepo = modelMapper.map(gitBranchRepoPostDto, GitBranchRepo.class);
        gitRepo.setType(GitType.branch);
        gitRepoRepository.save(gitRepo);
    }


}
