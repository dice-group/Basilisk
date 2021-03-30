package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.dto.git.GitBranchRepoDto;
import basilisk.hooksCheckingService.dto.git.GitRepoDto;
import basilisk.hooksCheckingService.events.GitRepoAddedEvent;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Fakhr Shaheen
 */

@Service
public class GitHooksService {

    private GitRepoRepository gitRepoRepository;
    private ModelMapper modelMapper;
    private MessageSender messageSender;


    public GitHooksService(GitRepoRepository gitRepoRepository, ModelMapper modelMapper,MessageSender messageSender) {
        this.gitRepoRepository = gitRepoRepository;
        this.modelMapper=modelMapper;
        this.messageSender=messageSender;
    }

    public List<GitRepoDto> findAllGitReleaseRepos()
    {
        var repos=gitRepoRepository.findAllByType(GitType.release);
        List<GitRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitRepoDto gitRepoDto=modelMapper.map(repo,GitRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }

        return gitRepoDtos;
    }

    public List<GitRepoDto> findAllGitPullRequestRepos()
    {
        var repos= gitRepoRepository.findAllByType(GitType.pull_request);
        List<GitRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitRepoDto gitRepoDto=modelMapper.map(repo,GitRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return gitRepoDtos;
    }

    public List<GitBranchRepoDto> findAllGitBranchRepos()
    {
        var repos= gitRepoRepository.findAllByType(GitType.branch);
        List<GitBranchRepoDto> gitRepoDtos=new ArrayList<>();
        for(GitRepo repo:repos)
        {
            GitBranchRepoDto gitRepoDto=modelMapper.map(repo,GitBranchRepoDto.class);
            gitRepoDtos.add(gitRepoDto);
        }
        return gitRepoDtos;
    }


    public GitRepoDto addGitRepoForRelease( GitRepoDto gitRepoDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoDto, GitRepo.class);
        GitRepo createdGitRepo=gitRepo;
        createdGitRepo.setType(GitType.release);
        //save it
        gitRepoRepository.save(createdGitRepo);
        // send it as event
        sendGitRepoAddedEvent(createdGitRepo);
        //return dto
        GitRepoDto createdGitRepoDto = modelMapper.map(createdGitRepo, GitRepoDto.class);
        return createdGitRepoDto;
    }

    public GitRepoDto addGitRepoForPullRequest(GitRepoDto gitRepoDto)
    {
        GitRepo gitRepo = modelMapper.map(gitRepoDto, GitRepo.class);
        GitRepo createdGitRepo=gitRepo;
        createdGitRepo.setType(GitType.pull_request);
        //save it
        gitRepoRepository.save(createdGitRepo);
        // send it as event
        sendGitRepoAddedEvent(createdGitRepo);
        //return dto
        GitRepoDto createdGitRepoDto = modelMapper.map(createdGitRepo, GitRepoDto.class);
        return createdGitRepoDto;
    }

    public GitBranchRepoDto addGitRepoForBranch(GitBranchRepoDto gitBranchRepoPostDto)
    {
        GitRepo gitRepo = modelMapper.map(gitBranchRepoPostDto, GitBranchRepo.class);
        GitRepo createdGitRepo=gitRepo;
        createdGitRepo.setType(GitType.branch);
        //save it
        gitRepoRepository.save(createdGitRepo);
        // send it as event
        sendGitRepoAddedEvent(createdGitRepo);
        // return dto
        GitBranchRepoDto createdGitRepoDto = modelMapper.map(createdGitRepo, GitBranchRepoDto.class);
        return createdGitRepoDto;

    }

    private void sendGitRepoAddedEvent(GitRepo createdGitRepo)
    {
        GitRepoAddedEvent gitRepoAddedEvent=modelMapper.map(createdGitRepo, GitRepoAddedEvent.class);
        try {
            messageSender.send(gitRepoAddedEvent);
        } catch (MessageSendingExecption messageSendingExecption) {
            //todo Log
        }
    }
}
