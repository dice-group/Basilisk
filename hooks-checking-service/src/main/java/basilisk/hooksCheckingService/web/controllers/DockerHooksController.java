package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.dto.docker.DockerRepoDto;
import basilisk.hooksCheckingService.services.HooksServices.DockerHooksService;
import com.google.common.base.Preconditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Fakhr Shaheen
 */

@RestController
@RequestMapping("hooks/docker")
public class DockerHooksController {

    private final DockerHooksService dockerHooksService;
    private final ModelMapper modelMapper;

    public DockerHooksController(DockerHooksService dockerHooksService, ModelMapper modelMapper) {
        this.dockerHooksService = dockerHooksService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DockerRepoDto>> getAllDockerRepos() {
        List<DockerRepoDto> repos = this.dockerHooksService.findAllDockerRepos()
                .stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<DockerRepoDto> addDockerRepo(@RequestBody DockerRepoDto dockerRepoDto) {
        Preconditions.checkNotNull(dockerRepoDto);
        DockerRepo dockerRepo = convertToEntity(dockerRepoDto);
        DockerRepo createdRepo = this.dockerHooksService.addDockerRepo(dockerRepo);
        return new ResponseEntity<>(convertToDto(createdRepo), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteDockerRepo(@PathVariable Long id) {
        Preconditions.checkNotNull(id);
        Optional<DockerRepo> repo = this.dockerHooksService.findDockerRepo(id);
        if (repo.isPresent()) {
            this.dockerHooksService.deleteHookForRepo(repo.get());
            return new ResponseEntity<>("The Docker repository has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There is no Docker repository with ID " + id, HttpStatus.OK);
        }

        // TODO send message?
    }

    private DockerRepoDto convertToDto(DockerRepo repo) {
        return this.modelMapper.map(repo, DockerRepoDto.class);
    }

    private DockerRepo convertToEntity(DockerRepoDto repoDto) {
        return this.modelMapper.map(repoDto, DockerRepo.class);
    }
}
