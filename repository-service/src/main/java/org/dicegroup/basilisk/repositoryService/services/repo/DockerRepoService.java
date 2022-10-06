package org.dicegroup.basilisk.repositoryService.services.repo;

import org.dicegroup.basilisk.repositoryService.model.repo.docker.DockerRepo;
import org.dicegroup.basilisk.repositoryService.repositories.repo.DockerRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DockerRepoService {

    private final DockerRepoRepository repoRepository;
    private final ModelMapper mapper;

    public DockerRepoService(DockerRepoRepository repository, ModelMapper mapper) {
        this.repoRepository = repository;
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

        return this.repoRepository.save(repo);
    }

    public void deleteRepo(DockerRepo repo) {
        this.repoRepository.delete(repo);
    }
}
