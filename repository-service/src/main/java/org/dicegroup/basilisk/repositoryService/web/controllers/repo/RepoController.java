package org.dicegroup.basilisk.repositoryService.web.controllers.repo;

import com.fasterxml.jackson.annotation.JsonView;
import org.dicegroup.basilisk.dto.Views;
import org.dicegroup.basilisk.dto.repo.DockerRepoDto;
import org.dicegroup.basilisk.dto.repo.GitRepoDto;
import org.dicegroup.basilisk.dto.repo.RepoDto;
import org.dicegroup.basilisk.repositoryService.model.repo.Repo;
import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerRepo;
import org.dicegroup.basilisk.repositoryService.model.repo.git.GitRepo;
import org.dicegroup.basilisk.repositoryService.repositories.repo.RepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("repos")
public class RepoController {
    private final RepoRepository repoRepository;
    private final ModelMapper mapper;

    public RepoController(RepoRepository repoRepository, ModelMapper mapper) {
        this.repoRepository = repoRepository;
        this.mapper = mapper;
    }

    @GetMapping
    @JsonView(Views.Api.class)
    public ResponseEntity<List<RepoDto>> getAllRepos() {
        List<RepoDto> repoDtos = convertToDtoList((List<Repo>) this.repoRepository.findAll());
        return new ResponseEntity<>(repoDtos, HttpStatus.OK);
    }

    private List<RepoDto> convertToDtoList(List<Repo> repos) {
        return repos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private RepoDto convertToDto(Repo repo) {
        if (repo.getClass().equals(GitRepo.class)) {
            return this.mapper.map(repo, GitRepoDto.class);
        } else if (repo.getClass().equals(DockerRepo.class)) {
            return this.mapper.map(repo, DockerRepoDto.class);
        } else {
            return null;
        }
    }
}
