package basilisk.hooksCheckingService.web.controllers;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.dto.docker.DockerRepoDto;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping()
    public List<DockerRepoDto> findAllDockerRepos() {
        var repos= dockerRepoRepository.findAll();
        List<DockerRepoDto> dockerRepoDtos=new ArrayList<>();
        for(DockerRepo repo:repos)
        {
            DockerRepoDto dockerRepoDto=modelMapper.map(repo,DockerRepoDto.class);
            dockerRepoDtos.add(dockerRepoDto);
        }
        return dockerRepoDtos;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addDockerRepo(@RequestBody DockerRepoDto dockerRepoDto)
    {
        DockerRepo dockerRepo=modelMapper.map(dockerRepoDto,DockerRepo.class);
        dockerRepoRepository.save(dockerRepo);
    }


}
