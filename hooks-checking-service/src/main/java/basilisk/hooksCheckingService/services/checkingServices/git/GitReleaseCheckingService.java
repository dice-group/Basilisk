package basilisk.hooksCheckingService.services.checkingServices.git;

import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.model.git.GitRepoType;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;


public class GitReleaseCheckingService extends GitCheckingService {


    public GitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return this.gitRepoRepository.findAllByRepoType(GitRepoType.RELEASE);
    }

    @Override
    public void checkRepo(GitRepo gitRepo) throws GithubException {
        try {

            //get the repo from github
            GHRepository repo = getRepoFromGitHub(gitRepo);

            //get latest release
            GHRelease release = repo.getLatestRelease();
            GHCommit commit = repo.getCommit(release.getTargetCommitish());

            //  check whether the hook is already saved
            checkHooks(gitRepo, repo, commit.getSHA1());

        } catch (Exception e) {
            throw new GithubException();
        }
    }

}
