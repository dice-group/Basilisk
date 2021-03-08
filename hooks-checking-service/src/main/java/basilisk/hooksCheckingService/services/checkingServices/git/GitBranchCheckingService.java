package basilisk.hooksCheckingService.services.checkingServices.git;


import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitHook;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.MessagingHandler;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.springframework.messaging.MessagingException;

import java.util.Optional;

public class GitBranchCheckingService extends GitCheckingService {

    public GitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessagingHandler hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByType(GitType.branch);
    }


    @Override
    protected void checkForNewVersion(GitRepo gitRepo) throws GithubException {

        try {

            GitBranchRepo gitBranchRepo = (GitBranchRepo) gitRepo;
            GHRepository repo = getRepoFromGitHub(gitRepo);

            //get latest commit on the branch
            GHBranch branch = repo.getBranch(gitBranchRepo.getBranchName());

            //  check whether the hook is already saved
            Optional<GitHook> foundHook = gitHookRepository.findByCommitSha1(branch.getSHA1());

            if (foundHook.isPresent()) {
                //ToDo nothing so far
            } else {
                //save the hook and send it as event
                GHCommit commit = repo.getCommit(branch.getSHA1());
                GitHook gitHook = GitHook.builder().gitRepo(gitRepo).commitCreationDate(commit.getCommitDate()).commitSha1(commit.getSHA1()).
                        commitUrl(commit.getHtmlUrl().toString()).build();

                gitHookRepository.save(gitHook);
                //send it to the queue
                messagingHandler.send(gitHook);

            }
        }
        catch (MessagingException e)
        {
            //ToDo
        }
        catch (Exception e)
        {
            throw new GithubException();
        }


    }

}
