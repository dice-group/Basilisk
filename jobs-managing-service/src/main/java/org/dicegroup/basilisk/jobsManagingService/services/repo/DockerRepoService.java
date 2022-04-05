package org.dicegroup.basilisk.jobsManagingService.services.repo;

import org.dicegroup.basilisk.events.hooks.repo.DockerRepoAddEvent;
import org.dicegroup.basilisk.events.hooks.repo.DockerRepoDeleteEvent;
import org.dicegroup.basilisk.jobsManagingService.model.repo.DockerRepo;
import org.dicegroup.basilisk.jobsManagingService.repositories.repo.DockerRepoRepository;
import org.dicegroup.basilisk.jobsManagingService.web.messaging.hooks.HooksMessageSender;
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

        DockerRepoAddEvent event = this.mapper.map(repo, DockerRepoAddEvent.class);
        this.messageSender.send(event);

        return createdRepo;
    }

    public void deleteRepo(DockerRepo repo) {
        DockerRepoDeleteEvent event = new DockerRepoDeleteEvent(repo.getId());
        this.messageSender.send(event);

        this.repoRepository.delete(repo);
    }
}
