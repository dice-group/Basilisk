package basilisk.hooksCheckingService.web.services.git;


import basilisk.hooksCheckingService.domain.git.GitHook;
import basilisk.hooksCheckingService.domain.git.GitType;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class GitBranchCheckingService extends GitCheckingService {

    public GitBranchCheckingService(GitRepoRepository gitRepoRepository, GitHookRepository gitHookRepository, HookMessageSender hookMessageSender) {
        super(gitRepoRepository, gitHookRepository, hookMessageSender);
    }

    @Override
    protected Iterable<GitRepo> getRelatedGitRepos() {
        return gitRepoRepository.findAllByType(GitType.branch);
    }


    @Override
    protected void checkForNewVersion(GitRepo gitRepo) throws IOException {

        GHRepository repo = getRepoFromGitHub(gitRepo);

        //get latest commit on the branch
        GHBranch branch = repo.getBranch("master");
        GHCommit commit = repo.getCommit(branch.getSHA1());


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
                    commitUrl(commit.getHtmlUrl().toString()).type(GitType.branch).build();

            gitHookRepository.save(gitHook);

        }


    }

}
