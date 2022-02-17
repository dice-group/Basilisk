package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.docker.DockerRepo;
import basilisk.hooksCheckingService.events.DockerRepoAddedEvent;
import basilisk.hooksCheckingService.repositories.DockerRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.apache.commons.collections4.IteratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Fakhr Shaheen, Fabian Rensing
 */
@Service
public class DockerHooksService {

    private final DockerRepoRepository dockerRepoRepository;
    private final MessageSender messageSender;
    private final ModelMapper modelMapper;

    public DockerHooksService(DockerRepoRepository dockerRepoRepository, ModelMapper modelMapper, MessageSender messageSender) {
        this.dockerRepoRepository = dockerRepoRepository;
        this.modelMapper = modelMapper;
        this.messageSender = messageSender;
    }

    public List<DockerRepo> findAllDockerRepos() {
        return IteratorUtils.toList(this.dockerRepoRepository.findAll().iterator());
    }

    public Optional<DockerRepo> findDockerRepo(long id) {
        return this.dockerRepoRepository.findById(id);
    }

    public DockerRepo addDockerRepo(DockerRepo dockerRepo) {
        //save it
        this.dockerRepoRepository.save(dockerRepo);
        // send it as event
        DockerRepoAddedEvent dockerRepoAddedEvent = this.modelMapper.map(dockerRepo, DockerRepoAddedEvent.class);
        try {
            this.messageSender.send(dockerRepoAddedEvent);
        } catch (MessageSendingExecption messageSendingExecption) {
            //ToDo log
        }
        return dockerRepo;
    }

    public void deleteHookForRepo(DockerRepo repo) {
        this.dockerRepoRepository.delete(repo);
    }
}
