package basilisk.hooksCheckingService.dto.git;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Fabian Rensing
 */

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
        GitBranchRepo repo = new GitBranchRepo();
        repo.setId(1L);
        repo.setRepoName("tentris");
        repo.setRepoOwner("dice-group");
        repo.setBranchName("test");

        GitBranchRepoDto repoDto = modelMapper.map(repo, GitBranchRepoDto.class);
        assertEquals(repo.getId(), repoDto.getId());
        assertEquals(repo.getRepoName(), repoDto.getRepoName());
        assertEquals(repo.getRepoOwner(), repoDto.getRepoOwner());
        assertEquals(repo.getBranchName(), repoDto.getBranchName());
    }

    @Test
    public void whenConvertDtoToEntityBranch() {
        GitBranchRepoDto repoDto = new GitBranchRepoDto();
        repoDto.setId(1L);
        repoDto.setRepoName("tentris");
        repoDto.setRepoOwner("dice-group");
        repoDto.setBranchName("test");

        GitBranchRepo repo = modelMapper.map(repoDto, GitBranchRepo.class);
        assertEquals(repoDto.getId(), repo.getId());
        assertEquals(repoDto.getRepoName(), repo.getRepoName());
        assertEquals(repoDto.getRepoOwner(), repo.getRepoOwner());
        assertEquals(repoDto.getBranchName(), repo.getBranchName());
    }
}