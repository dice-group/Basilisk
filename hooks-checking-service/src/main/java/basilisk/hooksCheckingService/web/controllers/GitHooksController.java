package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.dto.git.GitBranchRepoDto;
import basilisk.hooksCheckingService.dto.git.GitRepoDto;
import basilisk.hooksCheckingService.services.HooksServices.GitHooksService;
import com.google.common.base.Preconditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Fakhr Shaheen, Fabian Rensing
 */

@RestController
@RequestMapping("hooks/git")
public class GitHooksController {

    private final GitHooksService gitHooksService;
    private final ModelMapper modelMapper;

    public GitHooksController(GitHooksService gitHooksService, ModelMapper modelMapper) {
        this.gitHooksService = gitHooksService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitRepoDto>> getAllGitRepos() {
        List<GitRepoDto> gitRepoDtos = this.gitHooksService.findAllGitRepos()
                .stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(gitRepoDtos, HttpStatus.OK);
    }

    @GetMapping("/release")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitRepoDto>> getAllGitReleaseRepos() {
        List<GitRepoDto> gitRepoDtos = this.gitHooksService.findAllGitReleaseRepos()
                .stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(gitRepoDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/release", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitRepoDto> addGitRepoForRelease(@RequestBody GitRepoDto gitRepoDto) {
        Preconditions.checkNotNull(gitRepoDto);
        GitRepo repo = convertToEntity(gitRepoDto);
        GitRepo createdRepo = this.gitHooksService.addGitRepoForRelease(repo);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.OK);
    }

    @GetMapping("/pullRequest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitRepoDto>> getAllGitPullRequestRepos() {
        List<GitRepoDto> gitRepoDtos = this.gitHooksService.findAllGitPullRequestRepos()
                .stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(gitRepoDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/pullRequest", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitRepoDto> addGitRepoForPullRequest(@RequestBody GitRepoDto gitRepoDto) {
        Preconditions.checkNotNull(gitRepoDto);
        GitRepo createdRepo = this.gitHooksService.addGitRepoForPullRequest(convertToEntity(gitRepoDto));
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.OK);
    }

    @GetMapping("/branch")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GitBranchRepoDto>> getAllGitBranchRepos() {
        List<GitBranchRepoDto> gitRepoDtos = this.gitHooksService.findAllGitBranchRepos()
                .stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(gitRepoDtos, HttpStatus.OK);
    }

    @PostMapping(path = "/branch", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<GitBranchRepoDto> addGitRepoForBranch(@RequestBody GitBranchRepoDto gitBranchRepoDto) {
        Preconditions.checkNotNull(gitBranchRepoDto);
        GitBranchRepo createdRepo = this.gitHooksService.addGitRepoForBranch(convertToEntity(gitBranchRepoDto));
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteGitRepo(@PathVariable Long id) {
        Preconditions.checkNotNull(id);
        Optional<GitRepo> repo = this.gitHooksService.findGitRepo(id);
        if (repo.isPresent()) {
            this.gitHooksService.deleteHookForRepo(repo.get());
            return new ResponseEntity<>("The Git repository has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no Git repository with ID " + id, HttpStatus.OK);
        }
    }

    private GitRepoDto convertToDto(GitRepo gitRepo) {
        return modelMapper.map(gitRepo, GitBranchRepoDto.class);
    }

    private GitBranchRepoDto convertToDto(GitBranchRepo gitBranchRepo) {
        return modelMapper.map(gitBranchRepo, GitBranchRepoDto.class);
    }

    private GitRepo convertToEntity(GitRepoDto gitRepoDto) {
        return modelMapper.map(gitRepoDto, GitRepo.class);
    }

    private GitBranchRepo convertToEntity(GitBranchRepoDto gitBranchRepoDto) {
        return modelMapper.map(gitBranchRepoDto, GitBranchRepo.class);
    }
}
