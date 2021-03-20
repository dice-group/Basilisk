package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.dto.docker.DockerRepoDto;
import basilisk.hooksCheckingService.services.HooksServices.DockerHooksService;
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
@RequestMapping("hooks/docker")
public class DockerHooksController {

    private DockerHooksService dockerHooksService;

    public DockerHooksController(DockerHooksService dockerHooksService) {
        this.dockerHooksService = dockerHooksService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DockerRepoDto>> getAllDockerRepos() {
        var repos= dockerHooksService.findAllDockerRepos();
        return new ResponseEntity<>(repos,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<DockerRepoDto> addDockerRepo(@RequestBody DockerRepoDto dockerRepoDto)
    {
        dockerHooksService.addDockerRepo(dockerRepoDto);
        return new ResponseEntity<>(dockerRepoDto,HttpStatus.CREATED);
    }


}
