package basilisk.jobsManagingService.web.controllers;

import basilisk.jobsManagingService.dto.DockerRepoDto;
import basilisk.jobsManagingService.dto.Views;
import basilisk.jobsManagingService.model.repo.DockerRepo;
import basilisk.jobsManagingService.services.repo.DockerRepoService;
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
@RequestMapping("repos/docker")
public class DockerRepoController {

    private final DockerRepoService repoService;
    private final ModelMapper modelMapper;

    public DockerRepoController(DockerRepoService repoService, ModelMapper mapper) {
        this.repoService = repoService;
        this.modelMapper = mapper;
    }

    @GetMapping
    @JsonView(Views.Api.class)
    public ResponseEntity<List<DockerRepoDto>> getAllRepos() {
        List<DockerRepoDto> repoDtoList = convertToDtoList(this.repoService.getAllRepos());
        return new ResponseEntity<>(repoDtoList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DockerRepoDto> addRepo(@RequestBody @NotNull DockerRepoDto repoDto) {
        DockerRepo createdRepo = this.repoService.addRepo(convertToEntity(repoDto));
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRepo(@PathVariable @NotNull Long id) {
        Optional<DockerRepo> repo = this.repoService.getRepo(id);
        if (repo.isPresent()) {
            this.repoService.deleteRepo(repo.get());
            return new ResponseEntity<>("The Docker repository has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no Docker repository with ID " + id, HttpStatus.OK);
        }
    }

    private List<DockerRepoDto> convertToDtoList(List<DockerRepo> repos) {
        return repos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DockerRepoDto convertToDto(DockerRepo repo) {
        return modelMapper.map(repo, DockerRepoDto.class);
    }

    private DockerRepo convertToEntity(DockerRepoDto repoDto) {
        return modelMapper.map(repoDto, DockerRepo.class);
    }
}
