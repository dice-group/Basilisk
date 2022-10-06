package org.dicegroup.basilisk.repositoryService.dto;

import org.dicegroup.basilisk.dto.repo.GitRepoDto;
import org.dicegroup.basilisk.repositoryService.model.repo.git.GitRepo;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GitRepoDtoTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertedEntityToDto() {
        GitRepo repo = new GitRepo();
        repo.setId(1L);
        repo.setRepoName("tentris");
        repo.setRepoOwner("dice-group");

        GitRepoDto repoDto = modelMapper.map(repo, GitRepoDto.class);
        assertEquals(repo.getId(), repoDto.getId());
        assertEquals(repo.getRepoName(), repoDto.getRepoName());
        assertEquals(repo.getRepoOwner(), repoDto.getRepoOwner());
    }

    @Test
    public void whenConvertDtoToEntity() {
        GitRepoDto repoDto = new GitRepoDto();
        repoDto.setId(1L);
        repoDto.setRepoName("tentris");
        repoDto.setRepoOwner("dice-group");

        GitRepo repo = modelMapper.map(repoDto, GitRepo.class);
        assertEquals(repoDto.getId(), repo.getId());
        assertEquals(repoDto.getRepoName(), repo.getRepoName());
        assertEquals(repoDto.getRepoOwner(), repo.getRepoOwner());
    }

    @Test
    public void whenConvertedEntityToDtoBranch() {
        GitRepo repo = new GitRepo();
        repo.setId(1L);
        repo.setRepoName("tentris");
        repo.setRepoOwner("dice-group");
        repo.setBranchName("test");

        GitRepoDto repoDto = modelMapper.map(repo, GitRepoDto.class);
        assertEquals(repo.getId(), repoDto.getId());
        assertEquals(repo.getRepoName(), repoDto.getRepoName());
        assertEquals(repo.getRepoOwner(), repoDto.getRepoOwner());
        assertEquals(repo.getBranchName(), repoDto.getBranchName());
    }

    @Test
    public void whenConvertDtoToEntityBranch() {
        GitRepoDto repoDto = new GitRepoDto();
        repoDto.setId(1L);
        repoDto.setRepoName("tentris");
        repoDto.setRepoOwner("dice-group");
        repoDto.setBranchName("test");

        GitRepo repo = modelMapper.map(repoDto, GitRepo.class);
        assertEquals(repoDto.getId(), repo.getId());
        assertEquals(repoDto.getRepoName(), repo.getRepoName());
        assertEquals(repoDto.getRepoOwner(), repo.getRepoOwner());
        assertEquals(repoDto.getBranchName(), repo.getBranchName());
    }
}
