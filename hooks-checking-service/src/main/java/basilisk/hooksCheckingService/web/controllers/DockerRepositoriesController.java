package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.dto.docker.DockerRepoPostDto;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fakhr Shaheen
 */

@RestController
@RequestMapping("repositories/docker")
public class DockerRepositoriesController {

    private DockerRepoRepository dockerRepoRepository;
    private ModelMapper modelMapper;

    public DockerRepositoriesController(DockerRepoRepository dockerRepoRepository, ModelMapper modelMapper) {
        this.dockerRepoRepository = dockerRepoRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addDockerRepo(@RequestBody DockerRepoPostDto dockerRepoPostDto)
    {
        DockerRepo dockerRepo=modelMapper.map(dockerRepoPostDto,DockerRepo.class);
        dockerRepoRepository.save(dockerRepo);
    }


}
