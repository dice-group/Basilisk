package basilisk.jobsManagingService.services.repo;

import basilisk.jobsManagingService.events.hooks.DockerRepoEvent;
import basilisk.jobsManagingService.events.hooks.RepoEventType;
import basilisk.jobsManagingService.model.repo.DockerRepo;
import basilisk.jobsManagingService.repositories.repo.DockerRepoRepository;
import basilisk.jobsManagingService.web.messaging.hooks.HooksMessageSender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DockerRepoService {

    private final DockerRepoRepository repoRepository;
    private final HooksMessageSender messageSender;
    private final ModelMapper mapper;

    public DockerRepoService(DockerRepoRepository repository, HooksMessageSender sender, ModelMapper mapper) {
        this.repoRepository = repository;
        this.messageSender = sender;
        this.mapper = mapper;
    }

    public Optional<DockerRepo> getRepo(Long id) {
        return this.repoRepository.findById(id);
    }

    public List<DockerRepo> getAllRepos() {
        return (List<DockerRepo>) this.repoRepository.findAll();
    }

    public DockerRepo addRepo(DockerRepo repo) {
        if (repo.getId() != null) {
            Optional<DockerRepo> oldRepo = getRepo(repo.getId());

            if (oldRepo.isPresent()) {
                DockerRepo or = oldRepo.get();
                this.mapper.map(repo, or);
                repo = or;
            }
        }
        DockerRepo createdRepo = this.repoRepository.save(repo);

        DockerRepoEvent event = new DockerRepoEvent(RepoEventType.CREATED, createdRepo);
        this.messageSender.send(event);

        return createdRepo;
    }

    public void deleteRepo(DockerRepo repo) {
        DockerRepoEvent event = new DockerRepoEvent(RepoEventType.DELETED, repo);
        this.messageSender.send(event);

        this.repoRepository.delete(repo);
    }
}
