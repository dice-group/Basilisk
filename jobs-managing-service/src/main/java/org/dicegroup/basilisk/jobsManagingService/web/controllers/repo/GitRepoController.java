package org.dicegroup.basilisk.jobsManagingService.web.controllers.repo;

import org.dicegroup.basilisk.jobsManagingService.dto.Views;
import org.dicegroup.basilisk.jobsManagingService.dto.repo.GitRepoDto;
import org.dicegroup.basilisk.jobsManagingService.model.benchmarking.TripleStore;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepo;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepoType;
import org.dicegroup.basilisk.jobsManagingService.services.benchmarking.TripleStoreService;
import org.dicegroup.basilisk.jobsManagingService.services.repo.GitRepoService;
import com.fasterxml.jackson.annotation.JsonView;
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
    private final TripleStoreService tsService;
    private final ModelMapper modelMapper;

    public GitRepoController(GitRepoService repoService, TripleStoreService tsService, ModelMapper mapper) {
        this.repoService = repoService;
        this.tsService = tsService;
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
        GitRepo repo = getRepoEntity(gitRepoDto);

        GitRepo createdRepo = this.repoService.addRepo(repo, GitRepoType.RELEASE);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @PostMapping(path = "/branch", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GitRepoDto> addBranchRepo(@RequestBody @NotNull GitRepoDto gitRepoDto) {
        GitRepo repo = getRepoEntity(gitRepoDto);

        GitRepo createdRepo = this.repoService.addRepo(repo, GitRepoType.BRANCH);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @PostMapping(path = "/pullRequest", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GitRepoDto> addPullRequestRepo(@RequestBody @NotNull GitRepoDto gitRepoDto) {
        GitRepo repo = getRepoEntity(gitRepoDto);

        GitRepo createdRepo = this.repoService.addRepo(repo, GitRepoType.PULL_REQUEST);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRepo(@PathVariable @NotNull Long id) {
        Optional<GitRepo> repo = this.repoService.getRepo(id);
        if (repo.isPresent()) {
            this.repoService.deleteRepo(repo.get());
            return new ResponseEntity<>("The Git repository has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no Git repository with ID " + id, HttpStatus.OK);
        }
    }

    private GitRepo getRepoEntity(GitRepoDto gitRepoDto) {
        GitRepo repo = convertToEntity(gitRepoDto);
        if (gitRepoDto.getTripleStore() != null && gitRepoDto.getTripleStore().getId() != null) {
            Optional<TripleStore> tsOptional = this.tsService.getTripleStore(gitRepoDto.getTripleStore().getId());
            tsOptional.ifPresent(repo::setTripleStore);
        }
        return repo;
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
