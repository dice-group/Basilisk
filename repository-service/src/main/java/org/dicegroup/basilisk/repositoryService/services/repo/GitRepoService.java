package org.dicegroup.basilisk.repositoryService.services.repo;

import org.dicegroup.basilisk.dto.repo.GitRepoType;
import org.dicegroup.basilisk.repositoryService.model.repo.git.GitRepo;
import org.dicegroup.basilisk.repositoryService.repositories.repo.GitRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GitRepoService {

    private final GitRepoRepository gitRepoRepository;
    private final ModelMapper mapper;

    public GitRepoService(GitRepoRepository repository, ModelMapper mapper) {
        this.gitRepoRepository = repository;
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

        return this.gitRepoRepository.save(repo);
    }

    public void deleteRepo(GitRepo repo) {
        this.gitRepoRepository.delete(repo);
    }
}
