package org.dicegroup.basilisk.jobsManagingService.services.repo;

import org.dicegroup.basilisk.events.hooks.repo.GitRepoAddEvent;
import org.dicegroup.basilisk.events.hooks.repo.GitRepoDeleteEvent;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepo;
import org.dicegroup.basilisk.jobsManagingService.model.repo.GitRepoType;
import org.dicegroup.basilisk.jobsManagingService.repositories.repo.GitRepoRepository;
import org.dicegroup.basilisk.jobsManagingService.web.messaging.hooks.HooksMessageSender;
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
        return this.gitRepoRepository.findAllByGitRepoType(type);
    }

    public GitRepo addRepo(GitRepo repo, GitRepoType type) {
        repo.setGitRepoType(type);

        if (repo.getId() != null) {
            Optional<GitRepo> oldRepo = getRepo(repo.getId());

            if (oldRepo.isPresent()) {
                GitRepo or = oldRepo.get();
                this.mapper.map(repo, or);
                repo = or;
            }
        }

        GitRepo createdRepo = this.gitRepoRepository.save(repo);

        GitRepoAddEvent event = this.mapper.map(repo, GitRepoAddEvent.class);
        this.messageSender.send(event);

        return createdRepo;
    }

    public void deleteRepo(GitRepo repo) {
        GitRepoDeleteEvent event = new GitRepoDeleteEvent(repo.getId());
        this.messageSender.send(event);

        this.gitRepoRepository.delete(repo);
    }
}
