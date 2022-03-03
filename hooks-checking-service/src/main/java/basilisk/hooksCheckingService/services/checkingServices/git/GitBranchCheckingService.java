package basilisk.hooksCheckingService.services.checkingServices.git;


import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.model.git.GitRepoType;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;
import org.springframework.messaging.MessagingException;


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
            checkHooks(gitRepo, repo, branch.getSHA1());

        } catch (MessagingException e) {
            // TODO
            throw e;
        } catch (Exception e) {
            throw new GithubException();
        }
    }

}
