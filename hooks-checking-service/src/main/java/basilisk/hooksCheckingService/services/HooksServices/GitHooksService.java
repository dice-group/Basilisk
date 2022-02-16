package basilisk.hooksCheckingService.services.HooksServices;

import basilisk.hooksCheckingService.core.exception.MessageSendingExecption;
import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.events.GitRepoAddedEvent;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.apache.commons.collections4.IteratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * @author Fakhr Shaheen, Fabian Rensing
 */

@Service
public class GitHooksService {

    private final GitRepoRepository gitRepoRepository;
    private final ModelMapper modelMapper;
    private final MessageSender messageSender;


    public GitHooksService(GitRepoRepository gitRepoRepository, ModelMapper modelMapper, MessageSender messageSender) {
        this.gitRepoRepository = gitRepoRepository;
        this.modelMapper = modelMapper;
        this.messageSender = messageSender;
    }

    public List<GitRepo> findAllGitReleaseRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAllByType(GitType.RELEASE).iterator());
    }

    public List<GitRepo> findAllGitPullRequestRepos() {
        return IteratorUtils.toList(this.gitRepoRepository.findAllByType(GitType.PULL_REQUEST).iterator());
    }

    public List<GitBranchRepo> findAllGitBranchRepos() {
        return StreamSupport
                .stream(this.gitRepoRepository.findAllByType(GitType.BRANCH).spliterator(), false)
                .map(repo -> modelMapper.map(repo, GitBranchRepo.class))
                .collect(Collectors.toList());
    }

    public GitRepo addGitRepo(GitRepo gitRepo, GitType gitType) {
        gitRepo.setType(gitType);
        //save it
        gitRepoRepository.save(gitRepo);
        // send it as event
        sendGitRepoAddedEvent(gitRepo);
        //return dto
        return gitRepo;
    }

    public GitRepo addGitRepoForRelease(GitRepo gitRepo) {
        return addGitRepo(gitRepo, GitType.RELEASE);
    }

    public GitRepo addGitRepoForPullRequest(GitRepo gitRepo) {
        return addGitRepo(gitRepo, GitType.PULL_REQUEST);
    }

    public GitBranchRepo addGitRepoForBranch(GitBranchRepo gitBranchRepo) {
        return (GitBranchRepo) addGitRepo(gitBranchRepo, GitType.BRANCH);
    }

    private void sendGitRepoAddedEvent(GitRepo createdGitRepo) {
        GitRepoAddedEvent gitRepoAddedEvent = modelMapper.map(createdGitRepo, GitRepoAddedEvent.class);
        try {
            messageSender.send(gitRepoAddedEvent);
        } catch (MessageSendingExecption messageSendingExecption) {
            //todo Log
        }
    }
}
