package basilisk.hooksCheckingService.web;

import basilisk.hooksCheckingService.domain.Hooks.Hook;
import basilisk.hooksCheckingService.domain.HooksRepos.GitBranchRepo;
import basilisk.hooksCheckingService.domain.HooksRepos.GitRepo;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import org.kohsuke.github.*;

import java.io.IOException;


public class GitBranchCheckingService extends GitCheckingService {

    GitBranchRepo gitBranchRepo;

    public GitBranchCheckingService(GitBranchRepo gitBranchRepo, GitHookRepository gitHookRepository) {
        super(gitHookRepository);
        this.gitBranchRepo = gitBranchRepo;
    }


    @Override
    protected void checkForNewVersion() throws IOException {
        GHRepository repo = getRepoFromGH(gitBranchRepo);

        GHBranch branch = repo.getBranch(gitBranchRepo.getBranchName());
        System.out.printf("");


    }

}
