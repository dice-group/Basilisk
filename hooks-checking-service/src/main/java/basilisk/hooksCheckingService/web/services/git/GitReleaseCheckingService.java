package basilisk.hooksCheckingService.web.services.git;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitHook;
import basilisk.hooksCheckingService.domain.git.GitHookType;
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
    public void checkForNewVersion(GitRepo gitRepo) throws IOException {
        //get the repo from github
        GHRepository repo = getRepoFromGH(gitRepo);

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
                    commitUrl(commit.getHtmlUrl().toString()).type(GitHookType.Release).build();

            gitHookRepository.save(gitHook);

        }




    }

}
