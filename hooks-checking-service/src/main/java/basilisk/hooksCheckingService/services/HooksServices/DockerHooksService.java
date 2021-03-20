package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.dto.docker.DockerRepoDto;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fakhr Shaheen
 */
@Service
public class DockerHooksService {

    private DockerRepoRepository dockerRepoRepository;
    private ModelMapper modelMapper;

    public DockerHooksService(DockerRepoRepository dockerRepoRepository,ModelMapper modelMapper) {
        this.dockerRepoRepository = dockerRepoRepository;
        this.modelMapper=modelMapper;
    }

    public List<DockerRepoDto> findAllDockerRepos() {
        var dockerRepos= dockerRepoRepository.findAll();
        List<DockerRepoDto> dockerRepoDtos=new ArrayList<>();
        for(DockerRepo repo:dockerRepos)
        {
            DockerRepoDto dockerRepoDto=modelMapper.map(repo,DockerRepoDto.class);
            dockerRepoDtos.add(dockerRepoDto);
        }
        return dockerRepoDtos;
    }

    public void addDockerRepo(DockerRepoDto dockerRepoDto)
    {
        DockerRepo dockerRepo=modelMapper.map(dockerRepoDto,DockerRepo.class);
        dockerRepoRepository.save(dockerRepo);
    }


}
