package basilisk.hooksCheckingService.services.checkingServices.git;


import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.events.git.GitCommitAddedEvent;
import basilisk.hooksCheckingService.model.git.GitHook;
import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.model.git.GitRepoType;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.springframework.messaging.MessagingException;

import java.util.Optional;


public class GitBranchCheckingService extends GitCheckingService {

    public GitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByRepoType(GitRepoType.BRANCH);
    }


    @Override
    protected void checkRepo(GitRepo gitRepo) throws GithubException {

        try {

            GHRepository repo = getRepoFromGitHub(gitRepo);

            //get latest commit on the branch
            GHBranch branch = repo.getBranch(gitRepo.getBranchName());

            //  check whether the hook is already saved
            Optional<GitHook> foundHook = gitHookRepository.findByCommitSha1(branch.getSHA1());

            if (foundHook.isEmpty()) {
                //save the hook and send it as event
                GHCommit commit = repo.getCommit(branch.getSHA1());
                GitHook gitHook = GitHook.builder()
                        .gitRepo(gitRepo)
                        .commitCreationDate(commit.getCommitDate())
                        .commitSha1(commit.getSHA1())
                        .commitUrl(commit.getHtmlUrl().toString())
                        .build();

                gitHookRepository.save(gitHook);
                //send git commit added event
                GitCommitAddedEvent gitCommitAddedEvent = GitCommitAddedEvent.builder()
                        .commit_sha1(gitHook.getCommitSha1())
                        .repoId(gitRepo.getId())
                        .url(gitHook.getCommitUrl())
                        .build();
                messageSender.send(gitCommitAddedEvent);

            }
        } catch (MessagingException e) {
            // TODO
            throw e;
        } catch (Exception e) {
            throw new GithubException();
        }


    }

}
