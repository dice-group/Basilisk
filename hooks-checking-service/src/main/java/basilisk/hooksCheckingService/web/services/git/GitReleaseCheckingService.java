package basilisk.hooksCheckingService.web.services.git;

import basilisk.hooksCheckingService.domain.git.GitHook;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;


import java.io.IOException;
import java.util.Optional;

public class GitReleaseCheckingService extends GitCheckingService{


    public GitReleaseCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByType(GitType.release);
    }


    @Override
    public void checkForNewVersion(GitRepo gitRepo) throws IOException {
        //get the repo from github
        GHRepository repo = getRepoFromGitHub(gitRepo);

        //get latest release
        GHRelease release=repo.getLatestRelease();
        GHCommit commit = repo.getCommit(release.getTargetCommitish());

             //  check whether the hook is already saved
        Optional<GitHook> foundHook = gitHookRepository.findByCommitSha1(commit.getSHA1());

        if(foundHook.isPresent())
        {
            //ToDo nothing so far
        }
        else
        {
            //add it to the database and send it as message
            GitHook gitHook=GitHook.builder().gitRepo(gitRepo).commitCreationDate(commit.getCommitDate()).commitSha1(commit.getSHA1()).
                    commitUrl(commit.getHtmlUrl().toString()).build();

            gitHookRepository.save(gitHook);

        }




    }

}
