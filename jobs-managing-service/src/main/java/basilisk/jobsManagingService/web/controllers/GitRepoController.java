package basilisk.jobsManagingService.web.controllers;

import basilisk.jobsManagingService.dto.GitRepoDto;
import basilisk.jobsManagingService.dto.Views;
import basilisk.jobsManagingService.model.repo.GitRepo;
import basilisk.jobsManagingService.model.repo.GitRepoType;
import basilisk.jobsManagingService.services.repo.GitRepoService;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("repos/git")
public class GitRepoController {

    private final GitRepoService repoService;
    private final ModelMapper modelMapper;

    public GitRepoController(GitRepoService repoService, ModelMapper mapper) {
        this.repoService = repoService;
        this.modelMapper = mapper;
    }

    @GetMapping
    @JsonView(Views.Api.class)
    public ResponseEntity<List<GitRepoDto>> getAllRepos() {
        List<GitRepoDto> gitRepoDtos = convertToDtoList(this.repoService.getAllRepos());
        return new ResponseEntity<>(gitRepoDtos, HttpStatus.OK);
    }

    @GetMapping("/release")
    @JsonView(Views.Api.class)
    public ResponseEntity<List<GitRepoDto>> getAllReleaseRepos() {
        List<GitRepoDto> repos = convertToDtoList(this.repoService.getAllReposByRepoType(GitRepoType.RELEASE));
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @GetMapping("/branch")
    @JsonView(Views.Api.class)
    public ResponseEntity<List<GitRepoDto>> getAllBranchRepos() {
        List<GitRepoDto> repos = convertToDtoList(this.repoService.getAllReposByRepoType(GitRepoType.BRANCH));
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @GetMapping("/pullRequest")
    @JsonView(Views.Api.class)
    public ResponseEntity<List<GitRepoDto>> getAllPullRequestRepos() {
        List<GitRepoDto> repos = convertToDtoList(this.repoService.getAllReposByRepoType(GitRepoType.PULL_REQUEST));
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @PostMapping(path = "/release", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GitRepoDto> addReleaseRepo(@RequestBody @NotNull GitRepoDto gitRepoDto) {
        GitRepo createdRepo = this.repoService.addRepo(convertToEntity(gitRepoDto), GitRepoType.RELEASE);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @PostMapping(path = "/branch", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GitRepoDto> addBranchRepo(@RequestBody @NotNull GitRepoDto gitRepoDto) {
        GitRepo createdRepo = this.repoService.addRepo(convertToEntity(gitRepoDto), GitRepoType.BRANCH);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @PostMapping(path = "/pullRequest", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GitRepoDto> addPullRequestRepo(@RequestBody @NotNull GitRepoDto gitRepoDto) {
        GitRepo createdRepo = this.repoService.addRepo(convertToEntity(gitRepoDto), GitRepoType.PULL_REQUEST);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRepo(@PathVariable Long id) {
        Preconditions.checkNotNull(id);
        Optional<GitRepo> repo = this.repoService.getRepo(id);
        if (repo.isPresent()) {
            this.repoService.deleteRepo(repo.get());
            return new ResponseEntity<>("The Git repository has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no Git repository with ID " + id, HttpStatus.OK);
        }
    }

    private List<GitRepoDto> convertToDtoList(List<GitRepo> repos) {
        return repos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private GitRepoDto convertToDto(GitRepo gitRepo) {
        return this.modelMapper.map(gitRepo, GitRepoDto.class);
    }

    private GitRepo convertToEntity(GitRepoDto gitRepoDto) {
        return this.modelMapper.map(gitRepoDto, GitRepo.class);
    }
}
