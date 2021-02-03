package basilisk.hooksCheckingService.web.services.git;

import basilisk.hooksCheckingService.domain.git.GitBranchRepo;
import basilisk.hooksCheckingService.domain.git.GitRepo;
import basilisk.hooksCheckingService.messaging.HookMessageSender;
import basilisk.hooksCheckingService.repositories.GitHookRepository;
import basilisk.hooksCheckingService.repositories.GitRepoRepository;
import basilisk.hooksCheckingService.web.services.CheckingService;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.util.Iterator;

public abstract class GitCheckingService implements CheckingService {


    protected final GitHookRepository gitHookRepository;
    protected final GitRepoRepository gitRepoRepository;
    protected final HookMessageSender hookMessageSender;

    public GitCheckingService( GitRepoRepository gitRepoRepository,GitHookRepository gitHookRepository,HookMessageSender hookMessageSender) {
        this.gitHookRepository = gitHookRepository;
        this.gitRepoRepository = gitRepoRepository;
        this.hookMessageSender = hookMessageSender;
    }

    public void performChecking() throws IOException {

        //get the github repos
        Iterable<GitRepo> gitRepos = getRelatedGitRepos();
        //go through them
        Iterator<GitRepo> repoIterator=gitRepos.iterator();
        while (repoIterator.hasNext())
        {
            GitRepo gitrepo =repoIterator.next();
            //do the logic for checking
            checkForNewVersion(gitrepo);
            //
        }

    }

    protected abstract Iterable<GitRepo> getRelatedGitRepos();

    protected abstract void checkForNewVersion(GitRepo gitrepo) throws IOException;


    protected GHRepository getRepoFromGH(GitRepo gitRepo) throws IOException {
        GitHub github;
        if (gitRepo.isPrivate())
            github = new GitHubBuilder().withOAuthToken(gitRepo.getOAuthToken()).build();
        else
            github =GitHub.connectAnonymously();

        GHRepository repo = github.getUser(gitRepo.getRepoOwner()).getRepository(gitRepo.getRepoName());

        return repo;
    }




}
