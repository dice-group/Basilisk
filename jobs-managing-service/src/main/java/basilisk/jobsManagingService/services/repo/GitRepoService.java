package basilisk.jobsManagingService.services.repo;

import basilisk.jobsManagingService.events.hooks.GitRepoAddedEvent;
import basilisk.jobsManagingService.events.hooks.GitRepoDeletedEvent;
import basilisk.jobsManagingService.model.repo.GitRepo;
import basilisk.jobsManagingService.model.repo.GitRepoType;
import basilisk.jobsManagingService.repositories.repo.GitRepoRepository;
import basilisk.jobsManagingService.web.messaging.hooks.HooksMessageSender;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GitRepoService {

    private final GitRepoRepository gitRepoRepository;
    private final HooksMessageSender messageSender;

    public GitRepoService(GitRepoRepository repository, HooksMessageSender sender) {
        this.gitRepoRepository = repository;
        this.messageSender = sender;
    }

    public Optional<GitRepo> getRepo(Long id) {
        return this.gitRepoRepository.findById(id);
    }

    public List<GitRepo> getAllRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAll().iterator());
    }

    public List<GitRepo> getAllReposByRepoType(GitRepoType type) {
        return IteratorUtils.toList(this.gitRepoRepository.findAllByRepoType(type).iterator());
    }

    public GitRepo addRepo(GitRepo repo, GitRepoType type) {
        repo.setRepoType(type);

        GitRepo createdRepo = this.gitRepoRepository.save(repo);

        GitRepoAddedEvent event = new GitRepoAddedEvent(createdRepo);
        this.messageSender.send(event);

        return createdRepo;
    }

    public void deleteRepo(GitRepo repo) {
        GitRepoDeletedEvent event = new GitRepoDeletedEvent(repo);
        this.messageSender.send(event);

        this.gitRepoRepository.delete(repo);
    }
}
