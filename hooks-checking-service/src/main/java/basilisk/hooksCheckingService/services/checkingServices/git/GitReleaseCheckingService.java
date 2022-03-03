package basilisk.hooksCheckingService.services.checkingServices.git;

import basilisk.hooksCheckingService.core.exception.GithubException;
import basilisk.hooksCheckingService.events.git.GitCommitAddedEvent;
import basilisk.hooksCheckingService.model.git.GitHook;
import basilisk.hooksCheckingService.model.git.GitRepo;
import basilisk.hooksCheckingService.model.git.GitRepoType;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.messaging.MessageSender;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;

import java.util.Optional;


public class GitReleaseCheckingService extends GitCheckingService {


    public GitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, MessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByRepoType(GitRepoType.RELEASE);
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
            Optional<GitHook> foundHook = gitHookRepository.findByCommitSha1(commit.getSHA1());

            if (foundHook.isEmpty()) {
                //add it to the database and send it as message
                GitHook gitHook = GitHook.builder().gitRepo(gitRepo).commitCreationDate(commit.getCommitDate()).commitSha1(commit.getSHA1()).
                        commitUrl(commit.getHtmlUrl().toString()).build();

                gitHookRepository.save(gitHook);
                //send git commit added event
                GitCommitAddedEvent gitCommitAddedEvent = GitCommitAddedEvent.builder()
                        .commit_sha1(gitHook.getCommitSha1())
                        .repoId(gitRepo.getId())
                        .url(gitHook.getCommitUrl())
                        .build();
                messageSender.send(gitCommitAddedEvent);

            }


        } catch (Exception e) {
            throw new GithubException();
        }
    }
}
