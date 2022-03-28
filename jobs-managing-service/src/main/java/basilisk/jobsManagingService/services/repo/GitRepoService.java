package basilisk.jobsManagingService.services.repo;

import basilisk.jobsManagingService.events.hooks.GitRepoEvent;
import basilisk.jobsManagingService.events.hooks.RepoEventType;
import basilisk.jobsManagingService.model.repo.GitRepo;
import basilisk.jobsManagingService.model.repo.GitRepoType;
import basilisk.jobsManagingService.repositories.repo.GitRepoRepository;
import basilisk.jobsManagingService.web.messaging.hooks.HooksMessageSender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GitRepoService {

    private final GitRepoRepository gitRepoRepository;
    private final HooksMessageSender messageSender;
    private final ModelMapper mapper;

    public GitRepoService(GitRepoRepository repository, HooksMessageSender sender, ModelMapper mapper) {
        this.gitRepoRepository = repository;
        this.messageSender = sender;
        this.mapper = mapper;
    }

    public Optional<GitRepo> getRepo(Long id) {
        return this.gitRepoRepository.findById(id);
    }

    public List<GitRepo> getAllRepos() {
        return (List<GitRepo>) this.gitRepoRepository.findAll();
    }

    public List<GitRepo> getAllReposByRepoType(GitRepoType type) {
        return this.gitRepoRepository.findAllByRepoType(type);
    }

    public GitRepo addRepo(GitRepo repo, GitRepoType type) {
        repo.setRepoType(type);

        if (repo.getId() != null) {
            Optional<GitRepo> oldRepo = getRepo(repo.getId());

            if (oldRepo.isPresent()) {
                GitRepo or = oldRepo.get();
                this.mapper.map(repo, or);
                repo = or;
            }
        }

        GitRepo createdRepo = this.gitRepoRepository.save(repo);

        GitRepoEvent event = new GitRepoEvent(RepoEventType.CREATED, createdRepo);
        this.messageSender.send(event);

        return createdRepo;
    }

    public void deleteRepo(GitRepo repo) {
        GitRepoEvent event = new GitRepoEvent(RepoEventType.DELETED, repo);
        this.messageSender.send(event);

        this.gitRepoRepository.delete(repo);
    }
}
