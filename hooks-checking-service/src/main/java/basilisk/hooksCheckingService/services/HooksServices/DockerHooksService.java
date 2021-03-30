package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.dto.docker.DockerRepoDto;
import basilisk.hooksCheckingService.events.DockerRepoAddedEvent;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
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
    private MessageSender messageSender;
    private ModelMapper modelMapper;

    public DockerHooksService(DockerRepoRepository dockerRepoRepository,ModelMapper modelMapper,MessageSender messageSender) {
        this.dockerRepoRepository = dockerRepoRepository;
        this.modelMapper=modelMapper;
        this.messageSender=messageSender;
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
        //save it
        dockerRepoRepository.save(dockerRepo);
        // send it as event
        DockerRepoAddedEvent dockerRepoAddedEvent=modelMapper.map(dockerRepo,DockerRepoAddedEvent.class);
        try {
            messageSender.send(dockerRepoAddedEvent);
        } catch (MessageSendingExecption messageSendingExecption) {
            //ToDo log
        }
    }


}
