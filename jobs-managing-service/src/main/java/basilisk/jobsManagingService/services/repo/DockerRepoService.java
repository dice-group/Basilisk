package basilisk.jobsManagingService.services.repo;

import basilisk.jobsManagingService.events.hooks.DockerRepoAddedEvent;
import basilisk.jobsManagingService.events.hooks.DockerRepoDeletedEvent;
import basilisk.jobsManagingService.model.repo.DockerRepo;
import basilisk.jobsManagingService.repositories.repo.DockerRepoRepository;
import basilisk.jobsManagingService.web.messaging.hooks.HooksMessageSender;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DockerRepoService {

    private final DockerRepoRepository repoRepository;
    private final HooksMessageSender messageSender;

    public DockerRepoService(DockerRepoRepository repository, HooksMessageSender sender) {
        this.repoRepository = repository;
        this.messageSender = sender;
    }

    public Optional<DockerRepo> getRepo(Long id) {
        return this.repoRepository.findById(id);
    }

    public List<DockerRepo> getAllRepos() {
        return IteratorUtils.toList(this.repoRepository.findAll().iterator());
    }

    public DockerRepo addRepo(DockerRepo repo) {
        DockerRepo createdRepo = this.repoRepository.save(repo);

        DockerRepoAddedEvent event = new DockerRepoAddedEvent(createdRepo);
        this.messageSender.send(event);

        return createdRepo;
    }

    public void deleteRepo(DockerRepo repo) {
        DockerRepoDeletedEvent event = new DockerRepoDeletedEvent(repo);
        this.messageSender.send(event);

        this.repoRepository.delete(repo);
    }
}
